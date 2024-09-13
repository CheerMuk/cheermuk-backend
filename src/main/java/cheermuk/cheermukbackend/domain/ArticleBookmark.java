package cheermuk.cheermukbackend.domain;

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
    @JoinColumn(name = "memberId")
    private Member member;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;
}
