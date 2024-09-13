package cheermuk.cheermukbackend.domain.Restaurant.entity;

import cheermuk.cheermukbackend.domain.JibunAddress;
import cheermuk.cheermukbackend.domain.Restaurant.dto.RestaurantRequest;
import cheermuk.cheermukbackend.domain.RoadAddress;
import cheermuk.cheermukbackend.domain.Restaurant.entity.constants.BizType;
import cheermuk.cheermukbackend.global.base.BaseSoftDeleteEntity;
import cheermuk.cheermukbackend.global.utils.GeomUtils;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.*;
import org.locationtech.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

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

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point coordinates;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BizType bizType;

    @Column
    private String link;

    @Builder
    private Restaurant(
            String name,
            JibunAddress jibunAddress,
            RoadAddress roadAddress,
            Point coordinates,
            BizType bizType,
            String link) {
        this.name = name;
        this.jibunAddress = jibunAddress;
        this.roadAddress = roadAddress;
        this.coordinates = coordinates;
        this.bizType = bizType;
        this.link = link;
    }

    public static Restaurant fromRequest(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .name(restaurantRequest.name())
                .jibunAddress(restaurantRequest.jibunAddress())
                .roadAddress(restaurantRequest.roadAddress())
                .coordinates(GeomUtils.createPoint(restaurantRequest.latitude(), restaurantRequest.longitude()))
                .bizType(restaurantRequest.bizType())
                .link(restaurantRequest.link())
                .build();
    }
}
