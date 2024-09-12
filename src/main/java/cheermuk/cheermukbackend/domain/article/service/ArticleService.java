package cheermuk.cheermukbackend.domain.article.service;

import cheermuk.cheermukbackend.domain.article.dto.ArticleRequest;
import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.domain.article.repository.ArticleRepository;
import cheermuk.cheermukbackend.global.exception.ArticleException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Page<Article> getArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Article getArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ErrorCode.NOT_FOUND_ARTICLE));
    }

    public Article addArticle(ArticleRequest articleRequest, Long memberId) {
        return articleRepository.save(Article.fromRequest(articleRequest, memberId));
    }

    public Article updateArticle(Long articleId, ArticleRequest articleRequest, Long memberId) {
        Article article = Article.fromRequest(articleRequest, memberId);
        article.setId(articleId);
        return articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
