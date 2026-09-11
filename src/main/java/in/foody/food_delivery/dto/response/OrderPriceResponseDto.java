package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPriceResponseDto {

    private Long id;

    private double itemTotal;
    private double  gstAmount;
    private double deliveryCharge;
    private double discount;
    private double finalPrice;


    private OrderResponseDto order;
}
