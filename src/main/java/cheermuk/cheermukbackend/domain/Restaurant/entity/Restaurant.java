package cheermuk.cheermukbackend.domain.Restaurant.entity;

import cheermuk.cheermukbackend.domain.JibunAddress;
import cheermuk.cheermukbackend.domain.Restaurant.dto.RestaurantRequest;
import cheermuk.cheermukbackend.domain.RoadAddress;
import cheermuk.cheermukbackend.domain.constants.BizType;
import cheermuk.cheermukbackend.global.base.BaseSoftDeleteEntity;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "restaurants")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@TypeDef(name = "json", typeClass = JsonType.class)
@SQLDelete(sql = "UPDATE restaurants SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")
@DynamicUpdate
public class Restaurant extends BaseSoftDeleteEntity {
    @Id
    @Setter
    @SequenceGenerator(name = "RESTAURANT_ID_GENERATOR", sequenceName = "seq_restaurant", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURANT_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JibunAddress jibunAddress;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private RoadAddress roadAddress;

    @Column(columnDefinition = "Numeric(10, 8)")
    private Double latitude;
    @Column(columnDefinition = "Numeric(11, 8)")
    private Double longitude;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BizType bizType;

    @Column
    private String link;

    @Builder
    private Restaurant(String name, JibunAddress jibunAddress, RoadAddress roadAddress, Double latitude, Double longitude, BizType bizType, String link) {
        this.name = name;
        this.jibunAddress = jibunAddress;
        this.roadAddress = roadAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bizType = bizType;
        this.link = link;
    }

    public static Restaurant fromRequest(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .name(restaurantRequest.name())
                .jibunAddress(restaurantRequest.jibunAddress())
                .roadAddress(restaurantRequest.roadAddress())
                .latitude(restaurantRequest.latitude())
                .longitude(restaurantRequest.longitude())
                .bizType(restaurantRequest.bizType())
                .link(restaurantRequest.link())
                .build();
    }
}
