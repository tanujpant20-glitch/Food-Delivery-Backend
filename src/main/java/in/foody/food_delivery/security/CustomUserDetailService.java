package in.foody.food_delivery.security;

import in.foody.food_delivery.exceptionHandling.UserNotFoundException;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    UserRepository userRepository;
    CustomUserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("no user found"));
        CustomUserDetail customUserDetail=new CustomUserDetail(user);
        return customUserDetail;
    }
}
