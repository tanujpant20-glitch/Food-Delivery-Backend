package in.foody.food_delivery.security;

import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.exceptionHandling.UserNotFoundException;
import in.foody.food_delivery.repository.DeliveryBoyRepository;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DeliveryBoyRepository deliveryBoyRepository;
    CustomUserDetailService(UserRepository userRepository
    ,DeliveryBoyRepository deliveryBoyRepository){
        this.userRepository=userRepository;
        this.deliveryBoyRepository=deliveryBoyRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        if(user.isPresent()){
            return new CustomUserDetail(user.get());
        }

        Optional<DeliveryBoy> deliveryBoy=deliveryBoyRepository.findByEmail(username);
        if(deliveryBoy.isPresent()){
            return new CustomUserDetail((deliveryBoy.get()));
        }

        throw new UsernameNotFoundException("User Not Found");
    }
}
