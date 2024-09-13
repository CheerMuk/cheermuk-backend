package cheermuk.cheermukbackend.domain.ArticleBookmark.repository;

import cheermuk.cheermukbackend.domain.ArticleBookmark.entity.ArticleBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleBookmarkRepository extends JpaRepository<ArticleBookmark, Long> {
    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);

    void deleteByArticleIdAndMemberId(Long articleId, Long memberId);
}
