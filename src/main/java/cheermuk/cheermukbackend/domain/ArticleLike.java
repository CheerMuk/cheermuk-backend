package cheermuk.cheermukbackend.domain;

import cheermuk.cheermukbackend.domain.base.BaseEntity;
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
    @JoinColumn(name = "articleId")
    private Article article;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
