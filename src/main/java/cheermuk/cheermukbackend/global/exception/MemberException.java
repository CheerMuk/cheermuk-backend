package cheermuk.cheermukbackend.global.exception;

import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends CustomException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberException(ErrorCode errorCode, Exception causeException) {
        super(errorCode, causeException);
    }
}
