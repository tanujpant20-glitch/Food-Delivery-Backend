package in.foody.food_delivery.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    private String Street;
    private String pincode;

    private String state;

    private String city;
    private String country;
    private String nearBy;
    private boolean isDefault;


}
