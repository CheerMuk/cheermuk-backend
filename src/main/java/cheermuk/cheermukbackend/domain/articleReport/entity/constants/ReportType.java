package cheermuk.cheermukbackend.domain.articleReport.entity.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReportType {
    ABUSE("욕설"),
    EXPOSE_PRIVACY("개인정보 노출"),
    ILLEGAL_INFO("불법 정보"),
    HARMFUL_TEEN("청소년에게 유해한 내용"),
    PORNO("음란물");

    private final String name;
}
