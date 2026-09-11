package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.enums.Currency;
import in.foody.food_delivery.entity.enums.PaymentMode;
import in.foody.food_delivery.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private Long id;

    private Long transactionId;


    private PaymentMode paymentMode;


    private PaymentStatus paymentStatus;

    private double amount;


    private Currency currency;

    private LocalDateTime createdAt;


    private OrderResponseDto order;
}
