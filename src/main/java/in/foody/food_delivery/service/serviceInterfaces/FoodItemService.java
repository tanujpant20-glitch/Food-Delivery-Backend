package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.request.FoodItemsRequestDto;
import in.foody.food_delivery.dto.response.FoodItemsResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface FoodItemService {
    public FoodItemsResponseDto addFood(FoodItemsRequestDto foodItemsRequestDto);
    public FoodItemsResponseDto updateFood(Long itemId,FoodItemsRequestDto foodItemsRequestDto);
    public String deleteFood(Long foodId );
    public FoodItemsResponseDto getFoodById(Long itemId);
    public Page<FoodItemsResponseDto> getAllFoodItems(int page, int size, String sortBy);
}
