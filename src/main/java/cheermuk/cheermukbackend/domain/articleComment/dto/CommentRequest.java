package cheermuk.cheermukbackend.domain.articleComment.dto;

import javax.validation.constraints.NotBlank;

public record CommentRequest(@NotBlank String content, Long parentCommentId) {}
