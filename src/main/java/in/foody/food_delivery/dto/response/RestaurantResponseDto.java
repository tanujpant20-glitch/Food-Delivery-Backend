package in.foody.food_delivery.dto.response;


import in.foody.food_delivery.entity.*;
import in.foody.food_delivery.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private int rating;
    private int totalRating;
    private String famousFood;
    private boolean isOpen;
    private LocalTime openTiming;
    private LocalTime closingTiming;
    private List<String> bannerImageUrl=new ArrayList<>();
    @Lob
    private String description;
    private Address address;
    private AccountStatus restaurantStatus;
    private List<FoodItemsResponseDto> foodItems=new ArrayList<>();
}
