package cheermuk.cheermukbackend.domain.Restaurant.service;

import cheermuk.cheermukbackend.domain.Restaurant.dto.RestaurantRequest;
import cheermuk.cheermukbackend.domain.Restaurant.entity.Restaurant;
import cheermuk.cheermukbackend.domain.Restaurant.repository.RestaurantRepository;
import cheermuk.cheermukbackend.global.exception.RestaurantException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Page<Restaurant> getRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(ErrorCode.NOT_FOUND_RESTAURANT));
    }

    public Restaurant addRestaurant(RestaurantRequest restaurantRequest) {
        return restaurantRepository.save(Restaurant.fromRequest(restaurantRequest));
    }

    public Restaurant updateRestaurant(RestaurantRequest restaurantRequest, Long restaurantId) {
        Restaurant restaurant = Restaurant.fromRequest(restaurantRequest);
        restaurant.setId(restaurantId);
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}
