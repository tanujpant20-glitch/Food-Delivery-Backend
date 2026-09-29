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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    private FoodItemsRepository foodItemsRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    public CartServiceImp(FoodItemsRepository foodItemsRepository
    ,UserRepository userRepository,
                          CartRepository cartRepository,ModelMapper modelMapper){
        this.foodItemsRepository=foodItemsRepository;
        this.userRepository=userRepository;
        this.cartRepository=cartRepository;
        this.modelMapper=modelMapper;
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

         Cart cart=cartRepository.findByCreator(user).orElseGet(
                 ()->{
                         Cart newcart=new Cart();
                         newcart.setCreator(user);
                         return newcart;
                 }
         );


         Optional<CartItem> existingItem=cart.getListFoodItems().stream().filter(x->x.getFoodItem().getId().equals(foodItems.getId())).findFirst();

        if(existingItem.isEmpty()){
            CartItem cartItem=new CartItem();
            cartItem.setFoodItem(foodItems);
            cartItem.setCart(cart);
            cartItem.setQuantities(addItemToCartdto.getQuantities());
            cartItem.setFinalPrice((foodItems.getPrice()* addItemToCartdto.getQuantities()));
            cart.getListFoodItems().add(cartItem);
        }else{
           CartItem item=existingItem.get();
           item.setQuantities(item.getQuantities()+addItemToCartdto.getQuantities());
           item.setFinalPrice(item.getQuantities()*foodItems.getPrice());
        }

        Double total=cart.getListFoodItems().stream().mapToDouble(x->x.getFinalPrice()).sum();
        cart.setCartTotal(total);
        Cart cartResponse=cartRepository.save(cart);
         CartResponseDto responseDto= modelMapper.map(cartResponse, CartResponseDto.class);
         return responseDto;
    }

    @Override
    public CartResponseDto getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedAccessException("You cannot access this feature"));

        Cart cart = cartRepository.findByCreator(user)
                .orElseThrow(() -> new CartDoesNotExistEception("Your cart is empty"));

        return modelMapper.map(cart, CartResponseDto.class);
    }

    @Override
    @Transactional
    public CartResponseDto removeFromCart( Long foodItemId) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you cannot access this feature"));

        Cart cart=cartRepository.findByCreator(user).orElseThrow(()->new CartDoesNotExistEception("Cart is empty or does not exist"));
        CartItem itemToRemove=user.getCart().getListFoodItems().stream().filter(x->x.getFoodItem().getId().equals(foodItemId)).findFirst().orElseThrow(()-> new FoodItemNotExistsException("No food item exists in your cart"));
        if(itemToRemove.getQuantities()>1){
            itemToRemove.setQuantities(itemToRemove.getQuantities()-1);
            itemToRemove.setFinalPrice(itemToRemove.getQuantities()*itemToRemove.getFoodItem().getPrice());
        }else{
            cart.getListFoodItems().remove(itemToRemove);
        }
       double total=cart.getListFoodItems().stream().mapToDouble(x->x.getFinalPrice()).sum();
        cart.setCartTotal(total);
       Cart cartSaved=cartRepository.save(cart);
       return modelMapper.map(cartSaved,CartResponseDto.class);
    }

    @Override
    @Transactional
    public String clearCart() {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(
                ()->new UnauthorizedAccessException("you are not authorized to do this operation"));
        Cart cart=cartRepository.findByCreator(user).orElseThrow(()->new CartDoesNotExistEception("no cart found"));
        cartRepository.delete(cart);
        return "Cart Cleared Successfully";
    }

}
