package cheermuk.cheermukbackend.domain.Restaurant.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JibunAddress {
    @NotBlank
    private String sido;
    @NotBlank
    private String sigungu;
    @NotBlank
    private String eupmeyondong;
    private String detail;
    @NotBlank
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
