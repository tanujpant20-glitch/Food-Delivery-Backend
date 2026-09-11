package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsResponseDto {


    private Long id;

    private OrderResponseDto order;


    private FoodItemsResponseDto foodItems;

    private int quantities;

}
