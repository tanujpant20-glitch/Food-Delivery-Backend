package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.OrderStatus;
import in.foody.food_delivery.entity.enums.PaymentMode;
import in.foody.food_delivery.entity.enums.PaymentStatus;
import jakarta.persistence.*;
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
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalTime orderedTime;
    private LocalDateTime orderedAt;
    @PrePersist
    public void ordertimedate(){
        orderedTime=LocalTime.now();
        orderedAt=LocalDateTime.now();
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_price_id", referencedColumnName = "id")
    private OrderPrice orderPrice;
    @ManyToOne
    @JoinColumn(name = "deliveryId")
    private DeliveryBoy deliveryBoy;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItemsList=new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
