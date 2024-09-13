package cheermuk.cheermukbackend.domain.ArticleLike.repository;

import cheermuk.cheermukbackend.domain.ArticleLike.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    boolean existsByMemberIdAndArticleId(Long memberId, Long articleId);

    void deleteByMemberIdAndArticleId(Long memberId, Long articleId);
}
