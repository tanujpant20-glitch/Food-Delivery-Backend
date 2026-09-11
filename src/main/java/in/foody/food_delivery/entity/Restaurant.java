package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private int totalRating;
    private String famousFood;
    private boolean isOpen;
    private LocalTime openTiming;
    private LocalTime closingTiming;
    private LocalDate createdAt;
    @PrePersist
    public void onCreated(){
        createdAt=LocalDate.now();
    }
    private List<String> bannerImageUrl=new ArrayList<>();
    @Lob
    private String description;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @Enumerated(EnumType.STRING)
    private AccountStatus restaurantStatus;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItems> foodItems=new ArrayList<>();
}
