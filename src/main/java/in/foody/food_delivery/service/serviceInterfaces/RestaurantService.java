package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.request.RestaurantRequestDto;
import in.foody.food_delivery.dto.response.RestaurantResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RestaurantService {

    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto);
    public RestaurantResponseDto updateRestaurant(RestaurantRequestDto restaurantRequestDto);
    public void deleteRestaurant(Long id);
    public RestaurantResponseDto getRestaurant(Long id);
    public List<RestaurantResponseDto> getAllRestaurant();
    public List<RestaurantResponseDto> getRestaurantsByOwner(Long ownerId);
    public List<RestaurantResponseDto> getRestaurantsIsActiveTrue(boolean isActive);
    public List<RestaurantResponseDto> getRestaurantsIsOpenFalse(boolean isActive);

}
