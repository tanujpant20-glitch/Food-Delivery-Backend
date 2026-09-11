package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public List<Restaurant> findByRestaurantStatus(AccountStatus status);

    public List<Restaurant> findByIsOpen(boolean open);
}
