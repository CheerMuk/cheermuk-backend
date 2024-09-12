package cheermuk.cheermukbackend.global.exception.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Member
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "유저 정보가 존재하지 않습니다."),

    // Article
    NOT_FOUND_ARTICLE(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),

    // Restaurant
    NOT_FOUND_RESTAURANT(HttpStatus.NOT_FOUND, "식당이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
