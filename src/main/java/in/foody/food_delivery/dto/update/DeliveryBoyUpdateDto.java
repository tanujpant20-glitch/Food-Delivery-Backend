package in.foody.food_delivery.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoyUpdateDto {
    private String name;

    private String phoneNumber;
    private String vehicleNumber;

    // Optional status toggle
    private Boolean isAvailable;
}
