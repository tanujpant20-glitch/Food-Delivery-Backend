package in.foody.food_delivery.dto.general;

import in.foody.food_delivery.entity.enums.OrderStatus;
import in.foody.food_delivery.entity.enums.PaymentMode;
import in.foody.food_delivery.entity.enums.PaymentStatus;

public class OrderPlacedRequest {

    private String userId;
    private String address;
    private Long restaurantId;
    private OrderStatus orderStatus=OrderStatus.ACCEPTED;
    private PaymentStatus paymentStatus=PaymentStatus.NOT_PAID;
    private PaymentMode paymentMode=PaymentMode.CASH_ON_DELIVERY;
}
