package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.authenticationDto.DeliveryPartnerRegisterDto;
import in.foody.food_delivery.dto.authenticationDto.JwtLoginRequestDto;
import in.foody.food_delivery.dto.authenticationDto.JwtResponseDto;
import in.foody.food_delivery.dto.authenticationDto.UserRegisterRequestDto;
import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.exceptionHandling.InvalidCredentialsException;
import in.foody.food_delivery.exceptionHandling.PasswordNotSameException;
import in.foody.food_delivery.exceptionHandling.UserAlreadyExistException;
import in.foody.food_delivery.exceptionHandling.UserNotFoundException;
import in.foody.food_delivery.repository.DeliveryBoyRepository;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.security.JwtUtils;
import in.foody.food_delivery.service.serviceInterfaces.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthenticationManager authenticationManager;

    DeliveryBoyRepository deliveryBoyRepository;
    JwtUtils jwtUtils;

    AuthServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder
    ,AuthenticationManager authenticationManager, JwtUtils jwtUtils
    , DeliveryBoyRepository deliveryBoyRepository){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtUtils=jwtUtils;
        this.deliveryBoyRepository=deliveryBoyRepository;
    }

    @Override
    @Transactional
    public UserResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        if(!userRegisterRequestDto.getPassword().equals(userRegisterRequestDto.getSamePassword())){
            throw new PasswordNotSameException("your password and confirm password are different");
        };

        if(userRepository.findByEmail(userRegisterRequestDto.getEmail()).isPresent()){
            throw new UserAlreadyExistException("user already exist");
        }


           User user=convertToUser(userRegisterRequestDto);
           User savedUser=userRepository.save(user);
        UserResponseDto userResponse=convertToResponseUser(user);
        return userResponse;
    }

    @Override
    @Transactional
    public JwtResponseDto loginUser(JwtLoginRequestDto jwtLoginRequestDto) {
        Authentication auth=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtLoginRequestDto.getEmail(),jwtLoginRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token=jwtUtils.generateJwtToken(auth);

        return new JwtResponseDto(token);
    }

    @Override
    public DeliveryBoyResponseDto registerDeliveryPartner(DeliveryPartnerRegisterDto registerDto) {
         if(!registerDto.getPassword().equals(registerDto.getSamePassword())){
             throw new PasswordNotSameException("your password and confirm password is not same");
         }

         if(deliveryBoyRepository.findByEmail(registerDto.getEmail()).isPresent()){
             throw new UserAlreadyExistException("user already exist by this email");
         }

         DeliveryBoy deliveryBoy=convertToDeliveryBoy(registerDto);
         deliveryBoyRepository.save(deliveryBoy);
         return convertToDeliveryBoyResponseDto(deliveryBoy);
    }

    private DeliveryBoyResponseDto convertToDeliveryBoyResponseDto(DeliveryBoy deliveryBoy){
        DeliveryBoyResponseDto deliveryBoyResponseDto=new DeliveryBoyResponseDto();
        deliveryBoyResponseDto.setEmail(deliveryBoy.getEmail());
        deliveryBoyResponseDto.setId(deliveryBoy.getId());
        deliveryBoyResponseDto.setUserName(deliveryBoy.getName());
        deliveryBoyResponseDto.setAccountStatus(deliveryBoy.getAccountStatus());
        return deliveryBoyResponseDto;
    }

    private User convertToUser(UserRegisterRequestDto userRegisterRequestDto){
        User user=new User();
        user.setUserName(userRegisterRequestDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequestDto.getPassword()));
        user.setEmail(userRegisterRequestDto.getEmail());
        return user;
    }

    private UserResponseDto convertToResponseUser(User user){
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setAccountStatus(user.getAccountStatus());
        return userResponseDto;
    }

    private     DeliveryBoy convertToDeliveryBoy(DeliveryPartnerRegisterDto deliveryPartnerRegisterDto){
        DeliveryBoy deliveryBoy=new DeliveryBoy();
        deliveryBoy.setName(deliveryPartnerRegisterDto.getName());
        deliveryBoy.setPanNumber(deliveryPartnerRegisterDto.getPanNumber());
        deliveryBoy.setEmail(deliveryPartnerRegisterDto.getEmail());
        deliveryBoy.setAge(deliveryPartnerRegisterDto.getAge());
        deliveryBoy.setPassword(bCryptPasswordEncoder.encode(deliveryPartnerRegisterDto.getPassword()));
        deliveryBoy.setAadhaarNumber(deliveryPartnerRegisterDto.getAadhaarNumber());
        deliveryBoy.setIfscCode(deliveryPartnerRegisterDto.getIfscCode());
        deliveryBoy.setBankAccountNumber(deliveryPartnerRegisterDto.getBankAccountNumber());
        deliveryBoy.setDrivingLicenseNumber(deliveryPartnerRegisterDto.getDrivingLicenseNumber());
        deliveryBoy.setVehicleType(deliveryPartnerRegisterDto.getVehicleType());
        deliveryBoy.setPhoneNumber(deliveryPartnerRegisterDto.getPhoneNumber());
        return deliveryBoy;
    }


}
