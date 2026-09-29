package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.request.FoodItemsRequestDto;
import in.foody.food_delivery.dto.response.FoodItemsResponseDto;
import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.exceptionHandling.*;
import in.foody.food_delivery.repository.FoodItemsRepository;
import in.foody.food_delivery.repository.RestaurantRepository;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.service.serviceInterfaces.FoodItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemImp implements FoodItemService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RestaurantRepository restaurantRepository;
    private FoodItemsRepository foodItemsRepository;
    public FoodItemImp(UserRepository userRepository,FoodItemsRepository foodItemsRepository
    ,RestaurantRepository restaurantRepository,
                       ModelMapper modelMapper){
        this.foodItemsRepository=foodItemsRepository;
        this.userRepository=userRepository;
        this.restaurantRepository=restaurantRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    @org.springframework.transaction.annotation.Transactional
    public FoodItemsResponseDto addFood(FoodItemsRequestDto foodItemsRequestDto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you are not authorised"));

        Restaurant restaurant = restaurantRepository.findByRestaurantOwner(user)
                    .orElseThrow(() -> new RestaurantNotFoundException("No restaurant associated found"));


            boolean itemExists = foodItemsRepository.existsByNameAndRestaurantAndIsDeletedIsFalse(foodItemsRequestDto.getName(), restaurant);
            if (itemExists) {
                throw new FoodItemAlreadyExistsException("A food item with this name already exists in your restaurant menu");
            }

            FoodItems foodItem = new FoodItems();
            foodItem.setName(foodItemsRequestDto.getName());
            foodItem.setLongDescription(foodItemsRequestDto.getLongDescription());
            foodItem.setShortDescription(foodItemsRequestDto.getShortDescription());
            if(foodItemsRequestDto.getPrice() == null||foodItemsRequestDto.getPrice()<0){
                throw new BadRequestException("Price must be greater than zero");
            }

            foodItem.setPrice(foodItemsRequestDto.getPrice());
            foodItem.setFoodType(foodItemsRequestDto.getFoodType());
            foodItem.getImageUrl().addAll(foodItemsRequestDto.getImageUrl());
            foodItem.setRestaurant(restaurant);
            foodItem.setAvailable(true);

            FoodItems savedFoodItem = foodItemsRepository.save(foodItem);
            return modelMapper.map(savedFoodItem, FoodItemsResponseDto.class);

    }

    @Override
    public FoodItemsResponseDto updateFood(Long itemId, FoodItemsRequestDto foodItemsRequestDto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you are not authorised"));

        Restaurant restaurant = restaurantRepository.findByRestaurantOwner(user)
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant Found"));

        boolean itemExists = foodItemsRepository.existsByNameAndRestaurantAndIsDeletedIsFalse(foodItemsRequestDto.getName(), restaurant);
        if(!itemExists){
            throw new FoodItemNotExistsException("A food item does exists in your restaurant menu");
        }

        FoodItems foodItem = new FoodItems();
        if(!foodItemsRequestDto.getName().trim().isEmpty())
        foodItem.setName(foodItemsRequestDto.getName());

        if(!foodItemsRequestDto.getLongDescription().trim().isEmpty())
        foodItem.setLongDescription(foodItemsRequestDto.getLongDescription());

        if(!foodItemsRequestDto.getShortDescription().trim().isEmpty())
        foodItem.setShortDescription(foodItemsRequestDto.getShortDescription());

        if(foodItemsRequestDto.getPrice()<0){
            throw new BadRequestException("Price must be greater than zero");
        }

        if(foodItemsRequestDto.getPrice() != null )
        foodItem.setPrice(foodItemsRequestDto.getPrice());
        if(foodItemsRequestDto.getFoodType()!=null)
        foodItem.setFoodType(foodItemsRequestDto.getFoodType());
        if(!foodItemsRequestDto.getImageUrl().isEmpty())
        foodItem.getImageUrl().addAll(foodItemsRequestDto.getImageUrl());

        foodItem.setRestaurant(restaurant);
        foodItem.setAvailable(true);


        return modelMapper.map(foodItem, FoodItemsResponseDto.class);

    }

    @Override
    @Transactional
    public String deleteFood(Long foodId) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you are not authorised"));

        FoodItems foodItems=foodItemsRepository.findById(foodId).orElseThrow(()->new FoodItemNotAvailableException("no food item exists"));

        if(user.getRestaurantsOwned().isEmpty() || foodItems.getRestaurant().getRestaurantOwner().equals(user)){
          throw new UnauthorizedAccessException(" You are not allowed to delete items from another restaurant");
        }
        foodItems.setDeleted(true);
        foodItemsRepository.save(foodItems);
        return "deleted successfully";

    }

    @Override
    public FoodItemsResponseDto getFoodById(Long itemId) {

        FoodItems foodItems=foodItemsRepository.findByIdAndIsDeletedIsFalse(itemId).orElseThrow(()->new FoodItemNotAvailableException("food item does not exists"));
        return modelMapper.map(foodItems, FoodItemsResponseDto.class);
    }

    @Override
    public Page<FoodItemsResponseDto> getAllFoodItems(int page, int size, String sortBy) {
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortBy).ascending());
        Page response=foodItemsRepository.findByIsAvailableTrue(pageable);

        return response.map(x->modelMapper.map(x,FoodItemsResponseDto.class));
    }


}
