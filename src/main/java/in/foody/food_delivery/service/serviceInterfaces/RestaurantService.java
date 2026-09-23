package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.request.RestaurantRequestDto;
import in.foody.food_delivery.dto.response.RestaurantResponseDto;
import in.foody.food_delivery.dto.update.RestaurantUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RestaurantService {

    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto);
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantUpdateDto restaurantRequestDto);
    public void deleteRestaurant(Long id);
    public RestaurantResponseDto getRestaurant(Long id);

    Page<RestaurantResponseDto> getAllRestaurant(Pageable page);

    public Page<RestaurantResponseDto> getRestaurantsIsOpenStatus(boolean isActive,int pageNo, int pageSize);

}
