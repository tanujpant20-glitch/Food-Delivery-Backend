package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.Cart;
import in.foody.food_delivery.entity.FoodItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {

    private Long id;

    private Long quantities;
    private double finalPrice;

    private FoodItemsResponseDto foodItem;

}
