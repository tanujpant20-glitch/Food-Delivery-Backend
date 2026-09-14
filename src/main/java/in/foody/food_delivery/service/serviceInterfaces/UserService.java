package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.authenticationDto.UserRegisterRequestDto;
import in.foody.food_delivery.dto.request.UserRequestDto;
import in.foody.food_delivery.dto.response.UserResponseDto;
import in.foody.food_delivery.entity.User;

import java.util.List;

public interface UserService {
    public UserResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto);
    public UserResponseDto updateUser(UserRequestDto user, Long id);
    public void deleteUser(Long id);
    public UserResponseDto getUser(Long id);
    public List<UserResponseDto> getAllUser();
    public UserResponseDto getUserByEmail(String email);
}
