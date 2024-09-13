package cheermuk.cheermukbackend.domain.articleComment.entity;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.domain.articleComment.dto.CommentRequest;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.global.base.BaseMutableEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_comments")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class ArticleComment extends BaseMutableEntity {
    @Id
    @Setter
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

    @Builder
    private ArticleComment(String content, Long parentCommentId, Long articleId, Long memberId) {
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.articleId = articleId;
        this.memberId = memberId;
    }

    public static ArticleComment fromRequest(Long articleId, Long memberId, CommentRequest commentRequest) {
        return ArticleComment.builder()
                .content(commentRequest.content())
                .articleId(articleId)
                .memberId(memberId)
                .parentCommentId(commentRequest.parentCommentId()).build();
    }
}
