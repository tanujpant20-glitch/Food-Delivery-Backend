package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.Cart;
import in.foody.food_delivery.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findByCart(Cart cart);
}
