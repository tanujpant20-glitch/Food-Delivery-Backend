package in.foody.food_delivery.dto.update;

import in.foody.food_delivery.entity.FoodItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateDto {
    private String name;
    private String famousFood;
    private Boolean isOpen;
    private LocalTime openTiming;
    private LocalTime closingTiming;
    private List<String> bannerImageUrl=new ArrayList<>();
    private String description;
    private List<FoodItems> foodItems=new ArrayList<>();
}
