package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.Order;
import in.foody.food_delivery.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    public Optional<Payment> findByTransactionId(Long id);

    public Optional<Payment> findByOrder(Order order);
}
