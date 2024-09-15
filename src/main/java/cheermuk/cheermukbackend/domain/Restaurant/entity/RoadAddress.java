package cheermuk.cheermukbackend.domain.Restaurant.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoadAddress {
    private String address;
    private String roadCode;

    private RoadAddress(String address, String roadCode) {
        this.address = address;
        this.roadCode = roadCode;
    }

    public static RoadAddress of(String address, String roadCode) {
        return new RoadAddress(address, roadCode);
    }
}
