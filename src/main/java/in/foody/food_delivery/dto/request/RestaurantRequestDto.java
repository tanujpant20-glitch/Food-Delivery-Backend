package in.foody.food_delivery.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import in.foody.food_delivery.entity.Address;
import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.Order;
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
public class RestaurantRequestDto {
    private String name;
    private String famousFood;
    private Boolean isOpen;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTiming;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingTiming;

    @ElementCollection
    private List<String> bannerImageUrl=new ArrayList<>();
    private String description;

    private AddressRequestDto address;

    private List<FoodItemsRequestDto> foodItems=new ArrayList<>();
}
