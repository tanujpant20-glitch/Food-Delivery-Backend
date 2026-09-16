package in.foody.food_delivery.config;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    )
    {
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder);
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            DaoAuthenticationProvider daoAuthenticationProvider
    ){
        return new ProviderManager(daoAuthenticationProvider) ;

    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        JwtAuthenticationConverter jwtAuthenticationConverter=new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                jwtGrantedAuthoritiesConverter
        );

        return jwtAuthenticationConverter;


    }
    @Bean
    public SecretKey jwtSecretKey(
            @Value("${jwt_secret_key}") String secret) {

        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey secretKey){
         return NimbusJwtEncoder
                 .withSecretKey(secretKey)
                 .algorithm(MacAlgorithm.HS256)
                 .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(
            SecretKey secretKey,
            @Value("${jwt_issuer}") String issuer
    ){
        NimbusJwtDecoder decoder =NimbusJwtDecoder.
                withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        decoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(issuer)
        );

        return decoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity,
                                                   JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception{
     httpSecurity.csrf(csrf-> csrf.disable())
             .authorizeHttpRequests(auth->auth
                     .requestMatchers("/api/auth/user/register", "/api/auth/user/login" , "/api/auth/deiveryBoy/register","/api/auth/delivery/login").permitAll()
                     .anyRequest().authenticated()
             ).
     sessionManagement(session -> session
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
             oauth2ResourceServer(oauth2->oauth2
                     .jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

     return httpSecurity.build();
    }

}
