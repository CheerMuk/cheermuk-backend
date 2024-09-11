package cheermuk.cheermukbackend.domain;

import cheermuk.cheermukbackend.global.base.BaseSoftDeleteEntity;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "articles")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
@SQLDelete(sql = "UPDATE articles SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Article extends BaseSoftDeleteEntity {
    @Id
    @SequenceGenerator(name = "ARTICLE_ID_GENERATOR", sequenceName = "seq_article", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Long viewCnt;
    @Column(nullable = false)
    private Long likeCnt;
    @Column
    private String link;

    @Type(type = "int-array")
    @Column(columnDefinition = "smallint[3]")
    private Integer[] rate = new Integer[3]; // taste_rate, price_rate, service_rate

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
