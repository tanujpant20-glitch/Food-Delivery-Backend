package in.foody.food_delivery.security;

import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.repository.DeliveryBoyRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDeliveryBoyDetailService implements UserDetailsService {

    private DeliveryBoyRepository deliveryBoyRepository;
    CustomDeliveryBoyDetailService(DeliveryBoyRepository deliveryBoyRepository){
        this.deliveryBoyRepository=deliveryBoyRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DeliveryBoy deliveryBoy= deliveryBoyRepository.findByName(username);
        CustomDeliveryBoyDetails customDeliveryBoyDetails=new CustomDeliveryBoyDetails(deliveryBoy);
        return customDeliveryBoyDetails;
    }
}
