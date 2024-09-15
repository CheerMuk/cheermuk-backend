package cheermuk.cheermukbackend.domain.ArticleImage.controller;

import cheermuk.cheermukbackend.domain.ArticleImage.dto.ImageRequest;
import cheermuk.cheermukbackend.domain.ArticleImage.dto.ImageResponse;
import cheermuk.cheermukbackend.domain.ArticleImage.service.ArticleImageService;
import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import cheermuk.cheermukbackend.global.exception.FileException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Controller
@RequestMapping("/api/v1/articles/{articleId}/images")
@RequiredArgsConstructor
public class ArticleImageController {
    private final ArticleImageService articleImageService;

    @GetMapping
    public ResponseEntity<List<ImageResponse>> getImages() {
        return ResponseEntity.ok(articleImageService.getImages().stream().map(ImageResponse::fromEntity).toList());
    }

    @PostMapping
    public ResponseEntity<List<ImageResponse>> addImages(@PathVariable Long articleId, @Size(min = 1, max = 3) List<MultipartFile> imgFiles) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleImageService.addImages(articleId, imgFiles).stream().map(ImageResponse::fromEntity).toList());
    }

    @PutMapping
    public ResponseEntity<List<ImageResponse>> updateImages(@PathVariable Long articleId, @RequestPart @Size(min = 1, max = 3) List<MultipartFile> imgFiles, @RequestPart @Valid ImageRequest imageRequest, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        if (imgFiles.size() != imageRequest.ids().size()) throw new FileException(ErrorCode.UNMATCHED_FILE_AND_ID);
        return ResponseEntity.ok(
                articleImageService
                        .updateImages(articleId, imgFiles, imageRequest, memberPrincipal.getUserRole(), memberPrincipal.id())
                        .stream()
                        .map(ImageResponse::fromEntity)
                        .toList());
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long articleId, @PathVariable Long imageId, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        articleImageService.deleteImage(articleId, imageId, memberPrincipal.getUserRole(), memberPrincipal.id());
        return ResponseEntity.noContent().build();
    }
}
