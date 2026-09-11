package in.foody.food_delivery.entity;

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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userid")
    private User creator;

    private LocalDateTime createdAt;
    @PrePersist
    public void oncreate(){
        createdAt=LocalDateTime.now();
    }
    @OneToMany(mappedBy = "cart")
    private List<CartItem> listFoodItems=new ArrayList<>();


}
