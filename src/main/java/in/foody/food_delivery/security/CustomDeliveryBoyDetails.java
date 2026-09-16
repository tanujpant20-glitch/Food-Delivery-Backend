package in.foody.food_delivery.security;

import in.foody.food_delivery.entity.DeliveryBoy;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomDeliveryBoyDetails implements UserDetails {
    DeliveryBoy deliveryBoy;
    CustomDeliveryBoyDetails(DeliveryBoy deliveryBoy){
        this.deliveryBoy=deliveryBoy;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(List.of(new SimpleGrantedAuthority(deliveryBoy.getRole().toString())));
    }

    @Override
    public @Nullable String getPassword() {
        return deliveryBoy.getPassword();
    }

    @Override
    public String getUsername() {
          return deliveryBoy.getName();
    }
}
