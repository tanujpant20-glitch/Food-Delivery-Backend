package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.authenticationDto.UserRegisterRequestDto;
import in.foody.food_delivery.dto.request.UserRequestDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.exceptionHandling.PasswordNotSameException;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.service.serviceInterfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    UserServiceImp(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto) {
       if(!userRegisterRequestDto.getPassword().equals(userRegisterRequestDto.getSamePassword())){
           throw new PasswordNotSameException("your password and confirm password are different");
       };
        User user=convertToUser(userRegisterRequestDto);
        User savedUser=userRepository.save(user);
       UserResponseDto userResponse=convertToResponseUser(user);
       return userResponse;
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto user, Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserResponseDto getUser(Long id) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return null;
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return null;
    }

    //conversion
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
}
