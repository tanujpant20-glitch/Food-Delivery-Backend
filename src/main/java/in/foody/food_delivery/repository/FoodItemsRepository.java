package in.foody.food_delivery.repository;

import in.foody.food_delivery.dto.response.FoodItemsResponseDto;
import in.foody.food_delivery.entity.FoodItems;
import in.foody.food_delivery.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItems,Long> {

    public List<FoodItems> findByRestaurantAndIsDeletedIsFalse(Restaurant restaurant);
    public boolean existsByNameAndRestaurantAndIsDeletedIsFalse(String name, Restaurant restaurant);

    public Optional<FoodItems> findByIdAndIsDeletedIsFalse(Long itemid);

    public Page<FoodItemsResponseDto> findByIsAvailableTrue(Pageable pageable);
}
