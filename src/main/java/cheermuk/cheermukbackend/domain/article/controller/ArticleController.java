package cheermuk.cheermukbackend.domain.article.controller;

import cheermuk.cheermukbackend.domain.article.dto.ArticleRequest;
import cheermuk.cheermukbackend.domain.article.dto.ArticleResponse;
import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.domain.article.service.ArticleService;
import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.DESC;


@Controller
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> getArticles(@QuerydslPredicate(root = Article.class) Predicate predicate, @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable) {
        return ResponseEntity.ok(articleService.getArticles(pageable).map(ArticleResponse::fromEntity));
    }

    @GetMapping("/nearest")
    public ResponseEntity<Page<ArticleResponse>> getNearestArticles(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Integer distance, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(articleService
                .getNearestArticles(latitude, longitude, distance, pageable)
                .map(ArticleResponse::fromEntity));
    }

    @GetMapping("/my")
    public ResponseEntity<Page<ArticleResponse>> getMyArticles(@PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return ResponseEntity.ok(articleService
                .getMyArticles(pageable, memberPrincipal.id())
                .map(ArticleResponse::fromEntity));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(ArticleResponse.fromEntity(articleService.getArticle(articleId)));
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody @Valid ArticleRequest articleRequest, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ArticleResponse.fromEntity(articleService.addArticle(articleRequest, memberPrincipal.id())));
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long articleId, @RequestBody @Valid ArticleRequest articleRequest, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        ArticleResponse articleResponse = ArticleResponse.fromEntity(articleService.updateArticle(articleId, articleRequest, memberPrincipal.id()));
        return ResponseEntity.ok(articleResponse);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }
}
