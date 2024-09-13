package cheermuk.cheermukbackend.domain.ArticleBookmark.service;

import cheermuk.cheermukbackend.domain.ArticleBookmark.controller.constants.BookmarkStatus;
import cheermuk.cheermukbackend.domain.ArticleBookmark.entity.ArticleBookmark;
import cheermuk.cheermukbackend.domain.ArticleBookmark.repository.ArticleBookmarkRepository;
import cheermuk.cheermukbackend.global.exception.ArticleException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleBookmarkService {
    private final ArticleBookmarkRepository articleBookmarkRepository;

    public void updateArticleBookmark(Long articleId, Long memberId, BookmarkStatus bookmarkStatus) {
        if (bookmarkStatus == BookmarkStatus.MARK) {
            // 이미 마킹했는지 확인
            if (articleBookmarkRepository.existsByArticleIdAndMemberId(articleId, memberId)) {
                throw new ArticleException(ErrorCode.ALREADY_EXISTS_BOOKMARK);
            }
            articleBookmarkRepository.save(ArticleBookmark.of(memberId, articleId));
        } else {
            // 이전에 마킹했었는지 확인
            if (!articleBookmarkRepository.existsByArticleIdAndMemberId(articleId, memberId)) {
                throw new ArticleException(ErrorCode.NOT_FOUND_BOOKMARK);
            }
            articleBookmarkRepository.deleteByArticleIdAndMemberId(articleId, memberId);
        }
    }
}
