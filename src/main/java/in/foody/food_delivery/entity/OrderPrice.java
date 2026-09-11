package in.foody.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double itemTotal;
    private double  gstAmount;
    private double deliveryCharge;
    private double discount;
    private double finalPrice;

    @OneToOne(mappedBy = "orderPrice")
    private Order order;
}
