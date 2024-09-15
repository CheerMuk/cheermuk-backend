package cheermuk.cheermukbackend.domain.Restaurant.dto;

import cheermuk.cheermukbackend.domain.Restaurant.entity.JibunAddress;
import cheermuk.cheermukbackend.domain.Restaurant.entity.Restaurant;
import cheermuk.cheermukbackend.domain.Restaurant.entity.RoadAddress;
import cheermuk.cheermukbackend.domain.Restaurant.entity.constants.BizType;
import lombok.Builder;

@Builder
public record RestaurantResponse(
        Long id, String name, JibunAddress jibunAddress, RoadAddress roadAddress, BizType bizType, String link) {
    public static RestaurantResponse fromEntity(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .jibunAddress(restaurant.getJibunAddress())
                .roadAddress(restaurant.getRoadAddress())
                .bizType(restaurant.getBizType())
                .link(restaurant.getLink())
                .build();
    }
}
