package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.FoodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String shortDescription;
    @Lob
    private String longDescription;
    private double price;
    private boolean isAvailable;
    private List<String> imageUrl=new ArrayList<>();
    private int rating;
    @OneToOne(mappedBy = "foodItem")
    private CartItem cartItem;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @OneToMany(mappedBy = "foodItems")
    private List<OrderItems> orderItems;

    @ManyToOne
    @JoinColumn(name = "restaurantid")
    private Restaurant restaurant;
}
