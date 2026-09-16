package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.authenticationDto.DeliveryPartnerRegisterDto;
import in.foody.food_delivery.dto.authenticationDto.JwtLoginRequestDto;
import in.foody.food_delivery.dto.authenticationDto.JwtResponseDto;
import in.foody.food_delivery.dto.authenticationDto.UserRegisterRequestDto;
import in.foody.food_delivery.dto.request.UserRequestDto;
import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.exceptionHandling.PasswordNotSameException;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.service.serviceInterfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {


    public UserResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
    public JwtResponseDto loginUser(JwtLoginRequestDto jwtLoginRequestDto);
    public DeliveryBoyResponseDto registerDeliveryPartner(DeliveryPartnerRegisterDto registerDto);
    //conversion

}
