package cheermuk.cheermukbackend.domain.ArticleLike.controller;

import cheermuk.cheermukbackend.domain.ArticleLike.controller.constants.LikeStatus;
import cheermuk.cheermukbackend.domain.ArticleLike.service.ArticleLikeService;
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
@RequestMapping("/api/v1/articles/{articleId}/likes")
@RequiredArgsConstructor
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @PostMapping
    public ResponseEntity<Void> updateArticleLike(
            @RequestParam LikeStatus likeStatus,
            @PathVariable Long articleId,
            @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        articleLikeService.updateArticleLike(likeStatus == LikeStatus.LIKE, memberPrincipal.id(), articleId);
        return ResponseEntity.ok(null);
    }
}
