package in.foody.food_delivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVerifyDto {

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}
