package in.foody.food_delivery.dto.request;

import in.foody.food_delivery.entity.Address;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String userName;
    private int age;

    private String password;

    private String email;

    private List<AddressRequestDto> addressList;


}
