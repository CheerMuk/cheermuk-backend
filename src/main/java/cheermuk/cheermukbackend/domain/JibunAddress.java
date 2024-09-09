package cheermuk.cheermukbackend.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JibunAddress {
    private String sido;
    private String sigungu;
    private String eupmeyondong;
    private String detail;
    private String postCode;

    @Builder
    private JibunAddress(String sido, String sigungu, String eupmeyondong, String detail, String postCode) {
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmeyondong = eupmeyondong;
        this.detail = detail;
        this.postCode = postCode;
    }
}
