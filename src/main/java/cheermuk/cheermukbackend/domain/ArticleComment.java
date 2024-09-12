package cheermuk.cheermukbackend.domain;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.global.base.BaseMutableEntity;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_comments")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleComment extends BaseMutableEntity {
    @Id
    @SequenceGenerator(name = "COMMENT_ID_GENERATOR", sequenceName = "seq_article_comment", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column
    private Long parentCommentId;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
