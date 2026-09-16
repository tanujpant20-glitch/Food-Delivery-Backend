package in.foody.food_delivery.entity;

import in.foody.food_delivery.entity.enums.AccountStatus;
import in.foody.food_delivery.entity.enums.Role;
import in.foody.food_delivery.entity.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role=Role.DELIVERYBOY;
    // Vehicle & Identity Details
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Column(unique = true)
    private String drivingLicenseNumber;

    @Column(nullable = false, unique = true)
    private String aadhaarNumber;

    @Column(nullable = false, unique = true)
    private String panNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus=AccountStatus.ISACTIVE;
    // Bank Details
    @Column(nullable = false)
    private String bankAccountNumber;

    private double earnings;

    @Column(nullable = false)
    private String ifscCode;

    // Operational Status
    @Column(nullable = false)
    private boolean isAvailable = false; // Online/Offline toggle

    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "deliveryBoy")
    private List<Order> orders=new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
