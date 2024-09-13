package cheermuk.cheermukbackend.domain.ArticleBookmark.controller;

import cheermuk.cheermukbackend.domain.ArticleBookmark.controller.constants.BookmarkStatus;
import cheermuk.cheermukbackend.domain.ArticleBookmark.service.ArticleBookmarkService;
import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/articles/{articleId}/bookmarks")
@RequiredArgsConstructor
public class ArticleBookmarkController {
    private final ArticleBookmarkService articleBookmarkService;

    @PostMapping
    public ResponseEntity<Void> updateArticleBookmark(@PathVariable Long articleId, @RequestParam BookmarkStatus bookmarkStatus, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        articleBookmarkService.updateArticleBookmark(articleId, memberPrincipal.id(), bookmarkStatus);
        return ResponseEntity.ok(null);
    }
}
