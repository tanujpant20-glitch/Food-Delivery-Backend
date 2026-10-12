package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.general.AddItemToCartDto;
import in.foody.food_delivery.dto.response.CartItemResponseDto;
import in.foody.food_delivery.dto.response.CartResponseDto;
import in.foody.food_delivery.dto.response.FoodItemsResponseDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.entity.Cart;
import in.foody.food_delivery.entity.CartItem;
import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.exceptionHandling.*;
import in.foody.food_delivery.repository.CartRepository;
import in.foody.food_delivery.repository.FoodItemsRepository;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.service.serviceInterfaces.CartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    private FoodItemsRepository foodItemsRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private static final String CART_PREFIX = "USER_CART:";
    public CartServiceImp(FoodItemsRepository foodItemsRepository
    ,UserRepository userRepository,
                          CartRepository cartRepository,ModelMapper modelMapper,
                          RedisTemplate<String, Object> redisTemplate){
        this.foodItemsRepository=foodItemsRepository;
        this.userRepository=userRepository;
        this.cartRepository=cartRepository;
        this.modelMapper=modelMapper;
        this.redisTemplate=redisTemplate;
    }

    @Override
    @Transactional
    public CartResponseDto addToCart(AddItemToCartDto addItemToCartdto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you cannot access this feature"));
         FoodItems foodItems =foodItemsRepository.findById(addItemToCartdto.getProductId()).orElseThrow(()->new FoodItemNotExistsException("FoodItem not exists"));
         if(!foodItems.isAvailable()){
             throw new FoodItemNotAvailableException("This Food Item does not available, please select another one");
         }

        String cartKey = "USER_CART:" + user.getId();
        String fieldKey = String.valueOf(foodItems.getId());
        Object existingObj = redisTemplate.opsForHash().get(cartKey, fieldKey);

        CartItemResponseDto cartItemResponseDto=new CartItemResponseDto();
         if(existingObj==null){
             cartItemResponseDto.setFoodItem(modelMapper.map(foodItems,FoodItemsResponseDto.class));
             cartItemResponseDto.setQuantities(addItemToCartdto.getQuantities());
             cartItemResponseDto.setFinalPrice((foodItems.getPrice()* addItemToCartdto.getQuantities()));
         }else{
              cartItemResponseDto=(CartItemResponseDto) existingObj;
             cartItemResponseDto.setQuantities(addItemToCartdto.getQuantities()+cartItemResponseDto.getQuantities());
             cartItemResponseDto.setFinalPrice(addItemToCartdto.getQuantities()*foodItems.getPrice());
         }

         CartItemResponseDto cartResponseDto=modelMapper.map(cartItemResponseDto, CartItemResponseDto.class);

        redisTemplate.opsForHash().put(cartKey, fieldKey, cartResponseDto);

        // 6. Fetch all items from Redis to calculate Cart Total & build Response
        List<Object> allItemsObj = redisTemplate.opsForHash().values(cartKey);
        List<CartItemResponseDto> cartItemList = new ArrayList<>();
        Double total=0.0;
        for (Object obj: allItemsObj){
            CartItemResponseDto item = (CartItemResponseDto) obj;
            cartItemList.add(item);
            total+=item.getFinalPrice();
        }
//
         CartResponseDto responseDto= new CartResponseDto();
         responseDto.setId(user.getId());
         responseDto.setCreatedAt(LocalDateTime.now());
         responseDto.setTotalPrice(total);
         responseDto.setListFoodItems(cartItemList);
         return responseDto;
    }

    @Override
    public CartResponseDto getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedAccessException("You cannot access this feature"));



        String cartKey=CART_PREFIX+user.getId();
        Map<Object, Object> entries=redisTemplate.opsForHash().entries(cartKey);

        if(entries.isEmpty()){
            throw new CartDoesNotExistEception("cart does not exists");
        }

        return buildCartResponse(user.getId(), cartKey);
    }

    @Override
    @Transactional
    public CartResponseDto removeFromCart( Long foodItemId) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you cannot access this feature"));

        String cartKey = CART_PREFIX + user.getId();
        String fieldKey = String.valueOf(foodItemId);

        Object existingObj=redisTemplate.opsForHash().get(cartKey,fieldKey);
        if(existingObj==null){
            throw new CartDoesNotExistEception("Cart is empty or does not exist");
        }

        CartItemResponseDto cartItemResponseDto=(CartItemResponseDto) existingObj;
        if(cartItemResponseDto.getQuantities()>1){
            cartItemResponseDto.setQuantities(cartItemResponseDto.getQuantities()-1);
            double singlePrice = cartItemResponseDto.getFinalPrice() / (cartItemResponseDto.getQuantities() + 1);
            cartItemResponseDto.setFinalPrice(singlePrice*cartItemResponseDto.getQuantities());
            redisTemplate.opsForHash().put(cartKey, fieldKey, cartItemResponseDto);
        }else{
            redisTemplate.opsForHash().delete(cartKey,fieldKey);
        }

        return buildCartResponse(user.getId(), cartKey);
    }

    @Override
    @Transactional
    public String clearCart() {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(
                ()->new UnauthorizedAccessException("you are not authorized to do this operation"));
        String cartKey = CART_PREFIX + user.getId();
        Boolean isDeleted = redisTemplate.delete(cartKey);

        if (Boolean.FALSE.equals(isDeleted)) {
            throw new CartDoesNotExistEception("No cart found to clear");
        }

        return "Cart Cleared Successfully";

    }

    private CartResponseDto buildCartResponse(Long userId, String cartKey) {
        Map<Object, Object> allItemsObj = redisTemplate.opsForHash().entries(cartKey);
        List<CartItemResponseDto> cartItemList = new ArrayList<>();
        double total = 0.0;

        for (Object obj : allItemsObj.values()) {
            CartItemResponseDto item = (CartItemResponseDto) obj;
            cartItemList.add(item);
            total += item.getFinalPrice();
        }

        CartResponseDto responseDto = new CartResponseDto();
        responseDto.setId(userId);
        responseDto.setCreatedAt(LocalDateTime.now());
        responseDto.setListFoodItems(cartItemList);
        responseDto.setTotalPrice(total); // Grand total set karne ke liye

        return responseDto;
    }
}
