package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class DeliveryBoyEarnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double deliveryFee;      // Base trip fee
    private Double tipAmount;        // Customer tip
    private Double bonusAmount;      // Surge / Peak hour bonus
    private Double totalEarning;      // deliveryFee + tipAmount + bonusAmount

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // PENDING, PAID, PROCESSING

    private LocalDateTime earnedAt;

    @ManyToOne
    @JoinColumn(name = "delivery_boy_id", nullable = false)
    private DeliveryBoy deliveryBoy;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
