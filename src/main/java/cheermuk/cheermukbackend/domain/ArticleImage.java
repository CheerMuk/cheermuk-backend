package cheermuk.cheermukbackend.domain;

import cheermuk.cheermukbackend.domain.base.BaseMutableEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_images")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleImage extends BaseMutableEntity {
    @Id
    @SequenceGenerator(name = "IMAGE_ID_GENERATOR", sequenceName = "seq_article_image", allocationSize = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;
}
