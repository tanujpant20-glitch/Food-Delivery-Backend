package in.foody.food_delivery.security;

import in.foody.food_delivery.entity.DeliveryBoy;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import in.foody.food_delivery.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor for Customer User
    public CustomUserDetail(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // Constructor for Delivery Partner
    public CustomUserDetail(DeliveryBoy deliveryBoy) {
        this.email = deliveryBoy.getEmail();
        this.password = deliveryBoy.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_DELIVERY_PARTNER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
