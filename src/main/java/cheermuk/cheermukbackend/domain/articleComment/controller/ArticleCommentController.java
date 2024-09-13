package cheermuk.cheermukbackend.domain.articleComment.controller;

import cheermuk.cheermukbackend.domain.articleComment.dto.CommentRequest;
import cheermuk.cheermukbackend.domain.articleComment.dto.CommentResponse;
import cheermuk.cheermukbackend.domain.articleComment.service.ArticleCommentService;
import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/articles/{articleId}/comments")
@RequiredArgsConstructor
public class ArticleCommentController {
    private final ArticleCommentService articleCommentService;

    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getParentComments(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(
                articleCommentService.getParentComments(pageable).map(CommentResponse::fromEntity));
    }

    @GetMapping("/{parentCommentId}")
    public ResponseEntity<Page<CommentResponse>> getChildComments(
            @PathVariable Long parentCommentId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(articleCommentService
                .getChildComments(pageable, parentCommentId)
                .map(CommentResponse::fromEntity));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long articleId,
            @RequestBody @Valid CommentRequest commentRequest,
            @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponse.fromEntity(
                articleCommentService.addComment(articleId, memberPrincipal.id(), commentRequest)));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody @Valid CommentRequest commentRequest, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return ResponseEntity.ok(CommentResponse.fromEntity(articleCommentService.updateComment(articleId, commentId, memberPrincipal.id(), commentRequest)));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        articleCommentService.deleteComment(commentId, memberPrincipal.id());
        return ResponseEntity.noContent().build();
    }
}
