package cheermuk.cheermukbackend.domain.Restaurant.entity.constants;

public enum BizType {
    SNACK_FOOD("분식"),
    JAPANESE("일식"),
    KOREAN("한식"),
    CHINESE("중국식"),
    CHICKEN("치킨"),
    LIGHT_WESTERN_FOOD("경양식"),
    TAVERN("주점"),
    CAFFE("카페"),
    LIVE_CAFFE("라이브 카페"),
    COLD_NOODLE("냉면"),
    FAMILY_RESTAURANT("패밀리 레스토랑"),
    FAST_FOOD("패스트푸드"),
    TEA_HOUSE("찻집"),
    ICE_CREAM("아이스크림"),
    SNACK("과자점"),
    BUFFET("뷔페"),
    FOREIGN_FOOD("외국음식전문점"),
    RAW_FISH("횟집"),
    ETC("기타");

    private final String name;

    BizType(String name) {
        this.name = name;
    }
}
