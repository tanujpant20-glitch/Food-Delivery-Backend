package in.foody.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "foodItemsid")
    private FoodItems foodItems;

    private int quantities;

    private double getActuallPriceOfOrder(){
        return quantities*foodItems.getPrice();
    }
}
