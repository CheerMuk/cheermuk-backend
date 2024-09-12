package cheermuk.cheermukbackend.domain.article.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record ArticleRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotNull @Size(min = 3, max = 3) Short[] rate,
        @NotNull Long restaurantId) {
}
