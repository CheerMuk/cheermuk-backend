package cheermuk.cheermukbackend.global.exception;

import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.Getter;

@Getter
public class RestaurantException extends CustomException {
    public RestaurantException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RestaurantException(ErrorCode errorCode, Exception causeException) {
        super(errorCode, causeException);
    }
}
