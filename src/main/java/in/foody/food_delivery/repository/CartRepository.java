package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.Cart;
import in.foody.food_delivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Optional<Cart> findByCreator(User user);
}
