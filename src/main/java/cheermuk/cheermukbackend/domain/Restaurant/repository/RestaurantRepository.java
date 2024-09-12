package cheermuk.cheermukbackend.domain.Restaurant.repository;

import cheermuk.cheermukbackend.domain.Restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}