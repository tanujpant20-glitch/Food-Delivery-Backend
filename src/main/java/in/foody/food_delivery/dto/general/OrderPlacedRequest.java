package in.foody.food_delivery.dto.general;

import in.foody.food_delivery.entity.enums.OrderStatus;
import in.foody.food_delivery.entity.enums.PaymentMode;
import in.foody.food_delivery.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedRequest {

    private String addressId;
    private Long restaurantId;
    private OrderStatus orderStatus=OrderStatus.ACCEPTED;
    private PaymentStatus paymentStatus=PaymentStatus.NOT_PAID;
    private PaymentMode paymentMode=PaymentMode.CASH_ON_DELIVERY;
}
