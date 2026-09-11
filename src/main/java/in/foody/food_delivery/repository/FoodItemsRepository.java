package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItems,Long> {

    public List<FoodItems> findByRestaurant(Restaurant restaurant);
}
