package cheermuk.cheermukbackend.domain.articleComment.dto;

import cheermuk.cheermukbackend.domain.articleComment.entity.ArticleComment;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record CommentResponse(Long id, String content, Timestamp created_at) {
    public static CommentResponse fromEntity(ArticleComment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .created_at(comment.getCreatedAt())
                .build();
    }
}
