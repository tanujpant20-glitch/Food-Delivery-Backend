package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.AccountStatus;
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
public class DeliveryBoy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String Password;
    private int age;
    private String email;

    private LocalDateTime deliverdTime;
    private double earnings;

    //relations
    @OneToMany(mappedBy = "deliveryBoy")
    private List<Order> orders=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
