package cheermuk.cheermukbackend.domain.ArticleImage.dto;

import javax.validation.constraints.Size;
import java.util.List;

public record ImageRequest (
    @Size(min = 1, max = 3) List<Long> ids
) {}
