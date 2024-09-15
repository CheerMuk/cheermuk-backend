package cheermuk.cheermukbackend.domain.ArticleImage.service.file;

import cheermuk.cheermukbackend.global.config.properties.LocalFileProperties;
import cheermuk.cheermukbackend.global.exception.FileException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalFileService implements FileService {
    private final LocalFileProperties properties;

    @Override
    public void uploadFile(MultipartFile file, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(createFileInstance(fileName))) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new FileException(ErrorCode.CANT_SAVE_FILE, e);
        }
    }

    @Override
    public void deleteFileIfExists(String fileName) {
        File file = createFileInstance(fileName);
        // 파일이 존재하지 않을 경우 예외 처리
        if (file.exists()) {
            // 파일을 정상적으로 삭제할 경우 true를 반환하며 false를 반환할 경우 예외 처리
            if (!file.delete()) {
                log.error("파일 삭제에 실패했습니다. fileName: {}", fileName);
            }
        } else {
            log.error("파일을 찾을 수 없습니다.");
        }
    }

    @Override
    public void deleteOrNotOldFile(String oldFileName, String newFileName) {
        if (StringUtils.hasText(oldFileName) && !oldFileName.equals(newFileName)) deleteFileIfExists(oldFileName);
    }

    private File createFileInstance(String fileName) {
        try {
            return new File(properties.path(), fileName);
        } catch (NullPointerException e) {
            throw new FileException(ErrorCode.NOT_FOUND_FILE, e);
        }
    }
}
