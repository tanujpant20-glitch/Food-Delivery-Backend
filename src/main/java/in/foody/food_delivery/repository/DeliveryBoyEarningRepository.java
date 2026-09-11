package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.entity.DeliveryBoyEarnings;
import in.foody.food_delivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryBoyEarningRepository extends JpaRepository<DeliveryBoyEarnings, Long> {

    public List<DeliveryBoyEarnings> findByDeliverBoy(DeliveryBoy deliveryBoy);

    public Optional<DeliveryBoyEarnings> findByOrder(Order order);
}
