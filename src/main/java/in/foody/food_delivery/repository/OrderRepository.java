package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByUser(User user);

    public List<Order> findByDeliveryBoy(DeliveryBoy deliveryBoy);

    public List<Order> findByRestaurant(Restaurant restaurant);
}
