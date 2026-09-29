package in.foody.food_delivery.dto.response;


import in.foody.food_delivery.entity.CartItem;
import in.foody.food_delivery.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {

    private Long id;


    private UserResponseDto creator;

    private LocalDateTime createdAt;

    @PrePersist
    public void create(){
        this.createdAt=LocalDateTime.now();
    }

    private List<CartItemResponseDto> listFoodItems=new ArrayList<>();
}
