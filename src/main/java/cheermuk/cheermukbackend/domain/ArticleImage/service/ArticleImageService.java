package cheermuk.cheermukbackend.domain.ArticleImage.service;

import cheermuk.cheermukbackend.domain.ArticleImage.dto.ImageRequest;
import cheermuk.cheermukbackend.domain.ArticleImage.dto.projections.ImgUrlSummary;
import cheermuk.cheermukbackend.domain.ArticleImage.entity.ArticleImage;
import cheermuk.cheermukbackend.domain.ArticleImage.repository.ArticleImageRepository;
import cheermuk.cheermukbackend.domain.ArticleImage.service.file.FileService;
import cheermuk.cheermukbackend.domain.article.service.ArticleService;
import cheermuk.cheermukbackend.domain.member.entity.constants.UserRole;
import cheermuk.cheermukbackend.global.config.properties.LocalFileProperties;
import cheermuk.cheermukbackend.global.exception.FileException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import cheermuk.cheermukbackend.global.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ArticleImageService {
    private final ArticleImageRepository articleImageRepository;
    private final ArticleService articleService;
    private final FileService fileService;
    private final LocalFileProperties properties;

    public List<ArticleImage> getImages() {
        return articleImageRepository.findAll();
    }

    public List<ArticleImage> addImages(Long articleId, List<MultipartFile> imgFiles) {
        return articleImageRepository.saveAll(imgFiles.stream().map((img) -> uploadFile(img, articleId)).toList());
    }

    public List<ArticleImage> updateImages(Long articleId, List<MultipartFile> imgFiles, ImageRequest imageRequest, UserRole userRole, Long memberId) {
        articleService.checkIfWriterOrAdmin(articleId, userRole, memberId);
        AtomicInteger index = new AtomicInteger(0);
        return articleImageRepository.saveAll(imgFiles.stream().map((img) -> {
            ArticleImage articleImage = uploadFile(img, articleId);
            Long imageId = imageRequest.ids().get(index.getAndIncrement());
            articleImage.setId(imageId);
            deleteFile(imageId);
            return articleImage;
        }).toList());
    }

    public void deleteImage(Long articleId, Long imageId, UserRole userRole, Long memberId) {
        articleService.checkIfWriterOrAdmin(articleId, userRole, memberId);
        deleteFile(imageId);
        articleImageRepository.deleteById(imageId);
    }

    private ArticleImage uploadFile(MultipartFile file, Long articleId) {
        if (FileUtils.isInValid(file)) throw new FileException(ErrorCode.INVALID_FILE);
        String fileName = FileUtils.generateUniqueFileName(file);
        fileService.uploadFile(file, fileName);
        return ArticleImage.of(joinPathAndFileName(fileName), articleId);
    }

    private void deleteFile(Long imageId) {
        String oldFileName = getImgUrl(imageId).imgUrl().replace(properties.path(), "");
        fileService.deleteFileIfExists(oldFileName);
    }

    private String joinPathAndFileName(String fileName) {
        return properties.path() + fileName;
    }

    private ImgUrlSummary getImgUrl(Long imageId) {
        return articleImageRepository.findImgUrlById(imageId).orElseThrow(() -> new FileException(ErrorCode.NOT_FOUND_FILE));
    }
}
