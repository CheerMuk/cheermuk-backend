package cheermuk.cheermukbackend.domain.article.dto;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record ArticleResponse(
        Long id,
        String title,
        String content,
        Long viewCnt,
        Long likeCnt,
        String link,
        Short[] rate,
        Timestamp createdAt
) {
    public static ArticleResponse fromEntity(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .viewCnt(article.getViewCnt())
                .likeCnt(article.getLikeCnt())
                .rate(article.getRate())
                .createdAt(article.getCreatedAt()).build();
    }
}
