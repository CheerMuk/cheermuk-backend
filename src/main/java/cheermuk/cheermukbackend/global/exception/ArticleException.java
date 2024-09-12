package cheermuk.cheermukbackend.global.exception;

import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.Getter;

@Getter
public class ArticleException extends CustomException {
    public ArticleException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ArticleException(ErrorCode errorCode, Exception causeException) {
        super(errorCode, causeException);
    }
}
