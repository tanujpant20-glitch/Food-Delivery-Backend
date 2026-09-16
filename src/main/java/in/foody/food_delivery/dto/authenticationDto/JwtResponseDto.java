package in.foody.food_delivery.dto.authenticationDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
    public JwtResponseDto(String token) {
        this.token = token;
    }
        private String token;
        private String type = "Bearer";


}
