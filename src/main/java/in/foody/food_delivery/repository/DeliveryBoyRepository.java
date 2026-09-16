package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.DeliveryBoy;
import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, Long> {

    public List<DeliveryBoy> findByAccountStatus(AccountStatus status);
    public DeliveryBoy findByName(String username);

    public Optional<DeliveryBoy> findByEmail(String email);
    public Optional<DeliveryBoy> findByOrders(Order order);
}
