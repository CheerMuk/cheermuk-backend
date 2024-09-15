package cheermuk.cheermukbackend.global.exception;

import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.Getter;

@Getter
public class FileException extends CustomException {
    public FileException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FileException(ErrorCode errorCode, Exception causeException) {
        super(errorCode, causeException);
    }
}
