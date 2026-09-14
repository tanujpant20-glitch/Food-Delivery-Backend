package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.AccountStatus;
import in.foody.food_delivery.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String email;
    private LocalDateTime createdAt;

    @PrePersist
    public void createDate(){
        this.createdAt=LocalDateTime.now();
    }
    //relations
    @OneToMany(mappedBy = "user")
    private List<Address> addresses=new ArrayList<>();



    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus=AccountStatus.ISACTIVE;

    @OneToMany(mappedBy = "user")
    private List<Order> orders=new ArrayList<>();

    @OneToMany(mappedBy = "restaurantOwner")
    private List<Restaurant> restaurantsOwned;
}
