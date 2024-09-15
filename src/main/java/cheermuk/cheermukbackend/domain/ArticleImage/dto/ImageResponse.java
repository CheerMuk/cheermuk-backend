package cheermuk.cheermukbackend.domain.ArticleImage.dto;

import cheermuk.cheermukbackend.domain.ArticleImage.entity.ArticleImage;

public record ImageResponse(Long id, String imgUrl) {
    public static ImageResponse fromEntity(ArticleImage articleImage) {
        return new ImageResponse(articleImage.getId(), articleImage.getImgUrl());
    }
}
