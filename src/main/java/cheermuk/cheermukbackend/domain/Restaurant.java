package cheermuk.cheermukbackend.domain;

import cheermuk.cheermukbackend.global.base.BaseSoftDeleteEntity;
import cheermuk.cheermukbackend.domain.constants.BizType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

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
public class Restaurant extends BaseSoftDeleteEntity {
    @Id
    @SequenceGenerator(name = "RESTAURANT_ID_GENERATOR", sequenceName = "seq_restaurant", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURANT_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

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
}
