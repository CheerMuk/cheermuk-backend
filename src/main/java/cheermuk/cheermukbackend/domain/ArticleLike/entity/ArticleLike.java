package cheermuk.cheermukbackend.domain.ArticleLike.entity;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.global.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_likes")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleLike extends BaseEntity {
    @Id
    @SequenceGenerator(name = "LIKE_ID_GENERATOR", sequenceName = "seq_article_like", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Article article;
    @Column(nullable = false)
    private Long articleId;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;
    @Column(nullable = false)
    private Long memberId;

    private ArticleLike(Long articleId, Long memberId) {
        this.articleId = articleId;
        this.memberId = memberId;
    }

    public static ArticleLike of(Long articleId, Long memberId) {
        return new ArticleLike(articleId, memberId);
    }
}
