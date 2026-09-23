package in.foody.food_delivery.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
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
public class FoodItemsRequestDto {
    private String shortDescription;
    private String longDescription;
    private double price;
    @JsonProperty("isAvailable")
    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean isAvailable=true;
    @ElementCollection
    private List<String> imageUrl=new ArrayList<>();
    private int rating;

    private FoodType foodType;

}
