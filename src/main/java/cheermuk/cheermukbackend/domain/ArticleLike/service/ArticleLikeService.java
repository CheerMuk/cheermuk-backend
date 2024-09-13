package cheermuk.cheermukbackend.domain.ArticleLike.service;

import cheermuk.cheermukbackend.domain.ArticleLike.entity.ArticleLike;
import cheermuk.cheermukbackend.domain.ArticleLike.repository.ArticleLikeRepository;
import cheermuk.cheermukbackend.domain.article.repository.ArticleRepository;
import cheermuk.cheermukbackend.global.exception.ArticleException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public void updateArticleLike(boolean isLike, Long memberId, Long articleId) {
        checkIfNotWriterForLike(articleId, memberId);
        if (isLike) {
            // 이미 추가했는지 확인
            if (articleLikeRepository.existsByMemberIdAndArticleId(memberId, articleId)) {
                throw new ArticleException(ErrorCode.ALREADY_EXISTS_LIKE);
            }
            articleLikeRepository.save(ArticleLike.of(articleId, memberId));
            articleRepository.updateLikeCnt(articleId, 1);
        } else {
            // 이전에 좋아요를 추가했었는지 확인
            if (!articleLikeRepository.existsByMemberIdAndArticleId(memberId, articleId)) {
                throw new ArticleException(ErrorCode.NOT_FOUND_LIKE);
            }
            articleLikeRepository.deleteByMemberIdAndArticleId(memberId, articleId);
            articleRepository.updateLikeCnt(articleId, -1);
        }
    }

    private void checkIfNotWriterForLike(Long articleId, Long memberId) {
        if (articleRepository.existsByIdAndMemberId(articleId, memberId))
            throw new ArticleException(ErrorCode.OWNER_ARTICLE_CANT_LIKE);
    }
}
