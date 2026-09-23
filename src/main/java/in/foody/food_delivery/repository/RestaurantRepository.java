package in.foody.food_delivery.repository;

import in.foody.food_delivery.entity.Address;
import in.foody.food_delivery.entity.Restaurant;
import in.foody.food_delivery.entity.enums.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public Page<Restaurant> findByRestaurantStatus(Pageable page, AccountStatus status);

    public Page<Restaurant> findByIsOpen(boolean open, Pageable pageable);
    public Optional<Restaurant> findById(Long id);
    public boolean existsByName(String name);

    public boolean existsByNameIgnoreCaseAndAddress_CityIgnoreCaseAndAddress_StreetIgnoreCase(String name, String city, String street);
}
