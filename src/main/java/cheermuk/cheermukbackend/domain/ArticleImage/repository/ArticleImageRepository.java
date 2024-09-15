package cheermuk.cheermukbackend.domain.ArticleImage.repository;

import cheermuk.cheermukbackend.domain.ArticleImage.dto.projections.ImgUrlSummary;
import cheermuk.cheermukbackend.domain.ArticleImage.entity.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
    Optional<ImgUrlSummary> findImgUrlById(Long imageId);
}
