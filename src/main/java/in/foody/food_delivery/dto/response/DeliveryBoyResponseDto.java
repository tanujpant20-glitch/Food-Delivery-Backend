package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.enums.AccountStatus;
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
public class DeliveryBoyResponseDto {

    private Long id;

    private String userName;
    private int age;
    private String email;

    private double earnings;

    private List<OrderResponseDto> orders=new ArrayList<>();

    private AccountStatus accountStatus;
}
