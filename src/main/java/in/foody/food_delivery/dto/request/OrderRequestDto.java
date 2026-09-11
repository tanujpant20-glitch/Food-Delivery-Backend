package in.foody.food_delivery.dto.request;

import in.foody.food_delivery.entity.*;
import in.foody.food_delivery.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private Long orderPriceId;
    private Long addressId;

    private Long restaurantId;


    private Long userId;

    private List<OrderItemsRequestDto> orderItemsList=new ArrayList<>();


    private Long paymentId;
}
