package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {

    public List<OrderItems> findByOrder(Order order);


}
