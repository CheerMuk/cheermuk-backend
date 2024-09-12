package cheermuk.cheermukbackend.domain.Restaurant.dto;

import cheermuk.cheermukbackend.domain.JibunAddress;
import cheermuk.cheermukbackend.domain.Restaurant.entity.Restaurant;
import cheermuk.cheermukbackend.domain.RoadAddress;
import cheermuk.cheermukbackend.domain.constants.BizType;
import lombok.Builder;

@Builder
public record RestaurantResponse(
        Long id,
        String name,
        JibunAddress jibunAddress,
        RoadAddress roadAddress,
        Double latitude,
        Double longitude,
        BizType bizType,
        String link) {
    public static RestaurantResponse fromEntity(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .jibunAddress(restaurant.getJibunAddress())
                .roadAddress(restaurant.getRoadAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .bizType(restaurant.getBizType())
                .link(restaurant.getLink())
                .build();
    }
}
