package in.foody.food_delivery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private Long id;
    private String street;
    private String nearBy;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private boolean isDefault;
    private LocalDateTime createdAt;
}
