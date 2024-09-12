package cheermuk.cheermukbackend.domain.article.repository;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
