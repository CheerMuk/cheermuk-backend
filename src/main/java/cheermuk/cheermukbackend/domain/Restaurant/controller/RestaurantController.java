package cheermuk.cheermukbackend.domain.Restaurant.controller;

import cheermuk.cheermukbackend.domain.Restaurant.dto.RestaurantRequest;
import cheermuk.cheermukbackend.domain.Restaurant.dto.RestaurantResponse;
import cheermuk.cheermukbackend.domain.Restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Page<RestaurantResponse>> getRestaurants(
            @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable) {
        return ResponseEntity.ok(restaurantService.getRestaurants(pageable).map(RestaurantResponse::fromEntity));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(RestaurantResponse.fromEntity(restaurantService.getRestaurant(restaurantId)));
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> addRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestaurantResponse.fromEntity(restaurantService.addRestaurant(restaurantRequest)));
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
            @PathVariable Long restaurantId, @RequestBody @Valid RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(
                RestaurantResponse.fromEntity(restaurantService.updateRestaurant(restaurantRequest, restaurantId)));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
