package in.foody.food_delivery.dto.response;


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
public class OrderResponseDto {

    private Long id;

    private OrderStatus orderStatus;

    private LocalTime orderedTime;
    private LocalDateTime orderedAt;


    private OrderPriceResponseDto orderPrice;

    private DeliveryBoyResponseDto deliveryBoy;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private RestaurantResponseDto restaurant;


    private UserResponseDto user;


    private List<OrderItemsResponseDto> orderItemsList=new ArrayList<>();


    private PaymentResponseDto payment;
}
