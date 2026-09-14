package in.foody.food_delivery.controller;

import in.foody.food_delivery.dto.authenticationDto.UserRegisterRequestDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.service.serviceInterfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/register")
public class AuthenticationUserRegisterController {
    UserService userService;
    AuthenticationUserRegisterController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerIdentities(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto){
         return ResponseEntity.
                 status(HttpStatus.CREATED)
                 .body(userService.createUser(userRegisterRequestDto));
    }
}
