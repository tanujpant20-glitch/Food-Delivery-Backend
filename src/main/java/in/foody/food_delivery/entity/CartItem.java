package in.foody.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantities;
    private double finalPrice;
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne
    @JoinColumn(name="foodItemId")
    private FoodItems foodItem;

    private double getTotalPriceWithQuantity(){
        return finalPrice*foodItem.getPrice();
    }
}
