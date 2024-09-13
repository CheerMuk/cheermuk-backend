package cheermuk.cheermukbackend.global.exception;

import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.Getter;

@Getter
public class CommentException extends CustomException {
    public CommentException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CommentException(ErrorCode errorCode, Exception causeException) {
        super(errorCode, causeException);
    }
}
