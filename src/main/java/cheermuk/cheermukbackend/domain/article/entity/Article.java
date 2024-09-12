package cheermuk.cheermukbackend.domain.article.entity;

import cheermuk.cheermukbackend.domain.Restaurant.entity.Restaurant;
import cheermuk.cheermukbackend.domain.article.dto.ArticleRequest;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.global.base.BaseSoftDeleteEntity;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
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
@DynamicUpdate
public class Article extends BaseSoftDeleteEntity {
    @Id
    @Setter
    @SequenceGenerator(name = "ARTICLE_ID_GENERATOR", sequenceName = "seq_article", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private long viewCnt;
    @Column(nullable = false)
    private long likeCnt;

    @Type(type = "int-array")
    @Column(columnDefinition = "smallint[3]")
    private Short[] rate = new Short[3]; // taste_rate, price_rate, service_rate

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", updatable = false, insertable = false)
    private Member member;
    @Column(nullable = false)
    private Long memberId;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId", updatable = false, insertable = false)
    private Restaurant restaurant;
    @Column(nullable = false)
    private Long restaurantId;

    @Builder
    private Article(String title, String content, Short[] rate, Long memberId, Long restaurantId) {
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.memberId = memberId;
        this.restaurantId = restaurantId;
    }

    public static Article fromRequest(ArticleRequest articleRequest, Long memberId) {
        return Article.builder()
                .title(articleRequest.title())
                .content(articleRequest.content())
                .rate(articleRequest.rate())
                .restaurantId(articleRequest.restaurantId())
                .memberId(memberId).build();
    }
}
