package cheermuk.cheermukbackend.domain.Restaurant.dto;

import cheermuk.cheermukbackend.domain.JibunAddress;
import cheermuk.cheermukbackend.domain.RoadAddress;
import cheermuk.cheermukbackend.domain.constants.BizType;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record RestaurantRequest(
        @NotBlank String name,
        @NotNull @Valid JibunAddress jibunAddress,
        RoadAddress roadAddress,
        @NotNull @Positive Double latitude,
        @NotNull @Positive Double longitude,
        @NotNull BizType bizType,
        String link
) {
}
