package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.CartItem;
import in.foody.food_delivery.entity.OrderItems;
import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.enums.FoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemsResponseDto {
    private Long id;

    private String shortDescription;

    private String longDescription;
    private double price;
    private boolean isAvailable;
    private List<String> imageUrl=new ArrayList<>();
    private int rating;


    private FoodType foodType;


}
