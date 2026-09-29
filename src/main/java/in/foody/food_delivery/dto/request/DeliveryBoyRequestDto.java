package in.foody.food_delivery.dto.request;

import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyRequestDto {


    private String userName;
    private String Password;
    private int age;
    private String email;

}
