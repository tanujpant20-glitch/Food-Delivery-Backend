package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.Currency;
import in.foody.food_delivery.entity.enums.PaymentMode;
import in.foody.food_delivery.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @OneToOne
    @JoinColumn(name="orderId")
    private Order order;


}
