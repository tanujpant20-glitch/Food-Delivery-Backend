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
    public FoodItems(FoodType foodType,String shortDescription, String longDescription
    ,double price, boolean isAvailable, List<String> imageUrl, int rating){
        this.foodType=foodType;
        this.longDescription=longDescription;
        this.shortDescription=shortDescription;
        this.price=price;
        this.isAvailable=isAvailable;
        this.imageUrl=imageUrl;
        this.rating=rating;
    }

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
