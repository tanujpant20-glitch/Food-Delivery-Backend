package in.foody.food_delivery.dto.response;

import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String userName;
    private String email;
    private int age;
    private AccountStatus accountStatus;
    private List<AddressResponseDto> addressList;
    private List<Restaurant> restaurantsOwned;
}
