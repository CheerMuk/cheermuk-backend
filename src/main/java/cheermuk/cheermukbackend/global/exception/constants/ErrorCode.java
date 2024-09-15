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
    OWNER_ARTICLE_CANT_LIKE(HttpStatus.BAD_REQUEST, "본인 게시글에는 좋아요를 추가/삭제할 수 없습니다."),
    ALREADY_EXISTS_LIKE(HttpStatus.BAD_REQUEST, "이미 좋아요를 추가했습니다."),
    NOT_FOUND_LIKE(HttpStatus.BAD_REQUEST, "좋아요가 추가되어있지 않습니다."),
    ALREADY_EXISTS_BOOKMARK(HttpStatus.BAD_REQUEST, "이미 북마크에 추가되었습니다."),
    NOT_FOUND_BOOKMARK(HttpStatus.BAD_REQUEST, "북마크가 추가되어있지 않습니다."),
    FORBIDDEN_ARTICLE(HttpStatus.FORBIDDEN, "게시글에 대한 권한이 없습니다."),

    // Restaurant
    NOT_FOUND_RESTAURANT(HttpStatus.NOT_FOUND, "식당이 존재하지 않습니다."),

    // Comment
    FORBIDDEN_COMMENT(HttpStatus.FORBIDDEN, "댓글에 대한 권한이 없습니다."),

    // Image
    INVALID_FIlE_NAME(HttpStatus.BAD_REQUEST, "파일명이 올바르지 않습니다."),

    // File
    CANT_SAVE_FILE(HttpStatus.BAD_REQUEST, "파일을 저장할 수 없습니다."),
    NOT_FOUND_FILE(HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다."),
    INVALID_FILE(HttpStatus.BAD_REQUEST, "파일이 비었거나 지원하지 않는 확장자입니다."),
    UNMATCHED_FILE_AND_ID(HttpStatus.BAD_REQUEST, "파일과 ID의 개수가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
