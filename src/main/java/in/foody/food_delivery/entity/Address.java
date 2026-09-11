package in.foody.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Street;
    private String pincode;
    private String state;
    private String city;
    private String country;
    private String nearBy;
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "address")
    private Restaurant restaurant;
    private double langitude;
    private double lattitude;
}
