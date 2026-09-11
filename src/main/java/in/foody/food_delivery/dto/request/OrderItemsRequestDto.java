package in.foody.food_delivery.dto.request;

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
public class OrderItemsRequestDto {

    private Long foodItemsId;

    private int quantities;

}
