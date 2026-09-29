package in.foody.food_delivery.controller;

import in.foody.food_delivery.dto.request.FoodItemsRequestDto;
import in.foody.food_delivery.dto.response.FoodItemsResponseDto;
import in.foody.food_delivery.service.implimentation.FoodItemImp;
import in.foody.food_delivery.service.serviceInterfaces.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FoodItemController {

    private final FoodItemImp foodItemService;

    public FoodItemController(FoodItemImp foodItemService) {
        this.foodItemService = foodItemService;
    }

    // 1. Add Food Item (Owner only)
    @PostMapping("private/foodItem/addFood")
    public ResponseEntity<FoodItemsResponseDto> addFood(@Valid @RequestBody FoodItemsRequestDto requestDto) {
        FoodItemsResponseDto response = foodItemService.addFood(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Update Food Item (Owner only)
    @PutMapping("private/foodItem/update/{itemId}")
    public ResponseEntity<FoodItemsResponseDto> updateFood(
            @PathVariable Long itemId,
            @RequestBody FoodItemsRequestDto requestDto) {
        FoodItemsResponseDto response = foodItemService.updateFood(itemId, requestDto);
        return ResponseEntity.ok(response);
    }

    // 3. Delete Food Item (Soft Delete - Owner only)
    @DeleteMapping("private/foodItem/delete/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable Long foodId) {
        String response = foodItemService.deleteFood(foodId);
        return ResponseEntity.ok(response);
    }

    // 4. Get Food Item by ID (Public)
    @GetMapping("public/foodItem/getFood/{itemId}")
    public ResponseEntity<FoodItemsResponseDto> getFoodById(@PathVariable Long itemId) {
        FoodItemsResponseDto response = foodItemService.getFoodById(itemId);
        return ResponseEntity.ok(response);
    }

    // 5. Get All Available Food Items with Pagination (Public)
    @GetMapping("public/foodItem/getAllFood")
    public ResponseEntity<Page<FoodItemsResponseDto>> getAllFoodItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Page<FoodItemsResponseDto> response = foodItemService.getAllFoodItems(page, size, sortBy);
        return ResponseEntity.ok(response);
    }
}
