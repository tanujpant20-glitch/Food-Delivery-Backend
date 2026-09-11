package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.AccountStatus;
import in.foody.food_delivery.entity.enums.Role;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userName;
    private int age;
    private String password;
    private String email;

    //relations
    @OneToMany(mappedBy = "user")
    private List<Address> addresses=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "user")
    private List<Order> orders=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Restaurant> restaurants;
}
