package in.foody.food_delivery.dto.request;

import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.enums.AccountStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class DeliveryBoyRequestDto {


    private String userName;
    private String Password;
    private int age;
    private String email;

}
