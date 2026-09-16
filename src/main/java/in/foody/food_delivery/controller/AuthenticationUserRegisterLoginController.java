package in.foody.food_delivery.controller;

import in.foody.food_delivery.dto.authenticationDto.DeliveryPartnerRegisterDto;
import in.foody.food_delivery.dto.authenticationDto.JwtLoginRequestDto;
import in.foody.food_delivery.dto.authenticationDto.JwtResponseDto;
import in.foody.food_delivery.dto.authenticationDto.UserRegisterRequestDto;
import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.service.implimentation.AuthServiceImp;
import in.foody.food_delivery.service.serviceInterfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationUserRegisterLoginController {
    AuthServiceImp authServiceimp;
    AuthenticationUserRegisterLoginController(AuthServiceImp authServiceimp){
        this.authServiceimp=authServiceimp;
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserResponseDto> registerIdentities(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto){
         return ResponseEntity.
                 status(HttpStatus.CREATED)
                 .body(authServiceimp.registerUser(userRegisterRequestDto));
    }


    @PostMapping("/user/login")
    public ResponseEntity<JwtResponseDto> loginIdentities(@Valid @RequestBody JwtLoginRequestDto jwtLoginRequestDto){
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(authServiceimp.loginUser( jwtLoginRequestDto));
    }

    @PostMapping("/deliveryPartner/register")
    public ResponseEntity<DeliveryBoyResponseDto> registerDeliveryBoy(@Valid @RequestBody DeliveryPartnerRegisterDto deliveryPartnerRegisterDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authServiceimp.registerDeliveryPartner(deliveryPartnerRegisterDto));
    }

    @PostMapping("/deliveryPartner/login")
    public ResponseEntity<JwtResponseDto> loginDeliveryBoy(@Valid @RequestBody JwtLoginRequestDto jwtLoginRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(authServiceimp.loginUser(jwtLoginRequestDto));
    }
}
