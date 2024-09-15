package cheermuk.cheermukbackend.domain.ArticleImage.entity;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.global.base.BaseMutableEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_images")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class ArticleImage extends BaseMutableEntity {
    @Id
    @Setter
    @SequenceGenerator(name = "IMAGE_ID_GENERATOR", sequenceName = "seq_article_image", allocationSize = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Article article;
    @Column(nullable = false)
    private Long articleId;

    private ArticleImage(String imgUrl, Long articleId) {
        this.imgUrl = imgUrl;
        this.articleId = articleId;
    }

    public static ArticleImage of(String imgUrl, Long articleId) {
        return new ArticleImage(imgUrl, articleId);
    }
}
