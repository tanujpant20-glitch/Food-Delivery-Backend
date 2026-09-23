package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {
    public Restaurant(Address address, String description, String name, List<FoodItems> foodItems,
               List<String> bannerImageUrl, LocalTime openTiming,LocalTime closingTiming,
               boolean isOpen, String famousFood){
        this.address=address;
        this.description=description;
        this.name=name;
        this.foodItems=foodItems;
        this.bannerImageUrl=bannerImageUrl;
        this.openTiming=openTiming;
        this.closingTiming=closingTiming;
        this.isOpen=isOpen;
        this.famousFood=famousFood;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;
    private int totalRating;
    private String famousFood;
    private Boolean isOpen;
    private LocalTime openTiming;
    private LocalTime closingTiming;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreated(){
        createdAt=LocalDate.now();
        updatedAt=LocalDateTime.now();
    }
    private List<String> bannerImageUrl=new ArrayList<>();
    @Lob
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "addressId")
    private Address address;

    @Enumerated(EnumType.STRING)
    private AccountStatus restaurantStatus=AccountStatus.ISACTIVE;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders=new ArrayList<>();


    @OneToMany(mappedBy = "restaurant")
    private List<FoodItems> foodItems=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid")
    private User restaurantOwner;
}
