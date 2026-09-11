package in.foody.food_delivery.dto.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemToCartDto {
    private Long productId;
    private Long userId;
    private Long quantities;
}
