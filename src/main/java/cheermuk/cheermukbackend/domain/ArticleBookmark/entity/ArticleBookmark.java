package cheermuk.cheermukbackend.domain.ArticleBookmark.entity;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.global.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_bookmarks")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleBookmark extends BaseEntity {
    @Id
    @SequenceGenerator(name = "BOOKMARK_ID_GENERATOR", sequenceName = "seq_article_bookmark", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKMARK_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;
    @Column(nullable = false)
    private Long memberId;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Article article;
    @Column(nullable = false)
    private Long articleId;

    private ArticleBookmark(Long memberId, Long articleId) {
        this.memberId = memberId;
        this.articleId = articleId;
    }

    public static ArticleBookmark of(Long memberId, Long articleId) {
        return new ArticleBookmark(memberId, articleId);
    }
}
