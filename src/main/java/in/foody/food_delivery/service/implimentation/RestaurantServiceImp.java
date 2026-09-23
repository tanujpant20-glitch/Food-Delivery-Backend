package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.request.AddressRequestDto;
import in.foody.food_delivery.dto.request.RestaurantRequestDto;
import in.foody.food_delivery.dto.response.FoodItemsResponseDto;
import in.foody.food_delivery.dto.response.RestaurantResponseDto;
import in.foody.food_delivery.dto.update.RestaurantUpdateDto;
import in.foody.food_delivery.entity.Address;
import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.entity.enums.AccountStatus;
import in.foody.food_delivery.exceptionHandling.RestaurantAlreadyExistException;
import in.foody.food_delivery.exceptionHandling.RestaurantNotExistException;
import in.foody.food_delivery.exceptionHandling.UnauthorizedAccessException;
import in.foody.food_delivery.exceptionHandling.UserNotFoundException;
import in.foody.food_delivery.repository.RestaurantRepository;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.service.serviceInterfaces.RestaurantService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    UserRepository userRepository;
    RestaurantRepository restaurantRepository;
    RestaurantServiceImp(RestaurantRepository restaurantRepository
    ,UserRepository userRepository){
        this.restaurantRepository=restaurantRepository;
        this.userRepository=userRepository;
    }
    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {

        String name= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        System.out.println(name);
        User owner=userRepository.findByEmail(name).orElseThrow( ()-> new UserNotFoundException("user does not exists"));

         if(restaurantRepository.existsByNameIgnoreCaseAndAddress_CityIgnoreCaseAndAddress_StreetIgnoreCase(restaurantRequestDto.getName(),restaurantRequestDto.getAddress().getCity(), restaurantRequestDto.getAddress().getStreet())){
             throw new RestaurantAlreadyExistException("Restaurant already exists");
         }
         Restaurant restaurant=convertToRestaurant(restaurantRequestDto);
         restaurant.setRestaurantOwner(owner);
         Restaurant response=restaurantRepository.save(restaurant);
       return convertToDto(response);
    }



    @Override
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantUpdateDto restaurantRequestDto) {
      Restaurant restaurant= restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotExistException("No restaurant exists with this id"));
      String email=SecurityContextHolder.getContext().getAuthentication().getName();
      if(!restaurant.getRestaurantOwner().getEmail().equalsIgnoreCase(email)){
          throw new UnauthorizedAccessException("Unauthorized action: Only the restaurant owner can perform updates.");
      }
         restaurant.setUpdatedAt(LocalDateTime.now());
        if (restaurantRequestDto.getName() != null && !restaurantRequestDto.getName().trim().isEmpty()) {
            restaurant.setName(restaurantRequestDto.getName());
        }
        if (restaurantRequestDto.getOpenTiming()!=null) {
            restaurant.setOpenTiming(restaurantRequestDto.getOpenTiming());
        }
        if (restaurantRequestDto.getClosingTiming() != null) {
            restaurant.setClosingTiming(restaurantRequestDto.getClosingTiming());
        }
        if (restaurantRequestDto.getIsOpen() != null) {
            restaurant.setIsOpen(restaurantRequestDto.getIsOpen());
        }
        if (restaurantRequestDto.getDescription() != null) {
            restaurant.setDescription(restaurantRequestDto.getDescription());
        }
        if (restaurantRequestDto.getBannerImageUrl() != null) {
                  restaurant.getBannerImageUrl().addAll(restaurantRequestDto.getBannerImageUrl());
        }
        if (restaurantRequestDto.getFoodItems() != null) {
                restaurant.getFoodItems().addAll(restaurantRequestDto.getFoodItems());
        }
        if (restaurantRequestDto.getFamousFood() != null) {
            restaurant.setFamousFood(restaurantRequestDto.getFamousFood());
        }
        Restaurant updatedRestaurant = restaurantRepository.saveAndFlush(restaurant);
     return convertToDto(updatedRestaurant);
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long id) {
         Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotExistException("No Restaurant exists"));
         restaurant.setRestaurantStatus(AccountStatus.DEACTIVATED);
         restaurant.setIsOpen(false);
         return;
    }

    @Override
    public RestaurantResponseDto getRestaurant(Long id) {
        Optional<Restaurant> response=restaurantRepository.findById(id);
        if(response.isPresent()){
            return convertToDto(response.get());
        }

        throw new RestaurantNotExistException("Restaurant did Not exists");
    }

    @Override
    public Page<RestaurantResponseDto> getAllRestaurant(Pageable page) {
            Page<Restaurant> restaurants=restaurantRepository.findByRestaurantStatus(page, AccountStatus.ISACTIVE);
        return restaurants
                .map(list->convertToDto(list));

    }

    @Override
    public Page<RestaurantResponseDto> getRestaurantsIsOpenStatus(boolean status, int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
       Page<Restaurant> response= restaurantRepository.findByIsOpen(status,pageable);
       return response.map(this::convertToDto);
    }

    //Conversions
    private RestaurantResponseDto convertToDto(Restaurant restaurants){
        RestaurantResponseDto responseDto=new RestaurantResponseDto();
        responseDto.setName(restaurants.getName());
        responseDto.setAddress(restaurants.getAddress());
        responseDto.setId(restaurants.getId());
        responseDto.setDescription(restaurants.getDescription());
        responseDto.setOpen(restaurants.getIsOpen());
        responseDto.setBannerImageUrl(restaurants.getBannerImageUrl());
        responseDto.setClosingTiming(restaurants.getClosingTiming());
        responseDto.setFamousFood(restaurants.getFamousFood());
       if(responseDto.getFoodItems()!=null){
           List<FoodItemsResponseDto> foodItemsResponseDtos=restaurants.getFoodItems()
                   .stream()
                   .map(item-> new FoodItemsResponseDto(item.getId(), item.getShortDescription(), item.getLongDescription(), item.getPrice(), item.isAvailable(), item.getImageUrl(),item.getRating(),item.getFoodType()))
                   .toList();

           responseDto.setFoodItems(foodItemsResponseDtos);
       }

       responseDto.setRestaurantStatus(AccountStatus.ISACTIVE);
       responseDto.setOpenTiming(restaurants.getOpenTiming());
       responseDto.setTotalRating(restaurants.getTotalRating());
       responseDto.setRating(restaurants.getRating());
       return responseDto;
    }

    private Address mapAddressDtoToEntity(AddressRequestDto dto) {
        if (dto == null) return null;

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPinCode());
        address.setCountry(dto.getCountry());

        return address;
    }
    private Restaurant convertToRestaurant(RestaurantRequestDto restaurantRequestDto) {

        Restaurant restaurant=new Restaurant(

                mapAddressDtoToEntity(restaurantRequestDto.getAddress()),
                restaurantRequestDto.getDescription(),
                restaurantRequestDto.getName(),
                restaurantRequestDto.getFoodItems()
                        .stream()
                        .map(item->new FoodItems(
                                item.getFoodType(),
                                item.getLongDescription(),
                                item.getShortDescription(),
                                item.getPrice(),
                                item.getIsAvailable(),
                                item.getImageUrl(),
                                item.getRating())).toList(),
                restaurantRequestDto.getBannerImageUrl(),
                restaurantRequestDto.getOpenTiming(),
                restaurantRequestDto.getClosingTiming(),
                restaurantRequestDto.getIsOpen(),
                restaurantRequestDto.getFamousFood()
        );
        restaurant.setRestaurantStatus(AccountStatus.ISACTIVE);
        return restaurant;
    }
}
