package cheermuk.cheermukbackend.domain.member.dto;

import cheermuk.cheermukbackend.global.exception.AuthException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthAttributes {
    private static final String KAKAO_REGISTRATION_ID = "Kakao";
    private static final String GOOGLE_REGISTRATION_ID = "Google";
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String imgUrl;
    private String provider;

    public static OAuthAttributes of(
            String name, String imgUrl, String provider, Map<String, Object> attributes, String nameAttributeKey) {
        return new OAuthAttributes(attributes, nameAttributeKey, name, imgUrl, provider);
    }

    public static OAuthAttributes of(
            String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (KAKAO_REGISTRATION_ID.equalsIgnoreCase(registrationId)) {
            return ofKakao("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.of(
                (String) attributes.get("name"),
                (String) attributes.get("picture"),
                GOOGLE_REGISTRATION_ID,
                attributes,
                userNameAttributeName);
    }

    @SuppressWarnings("unchecked")
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> properties = (Map<String, Object>) Optional.of(attributes.get("properties"))
                .orElseThrow(() -> new AuthException("Invalid Kakao OAuth Request"));

        return OAuthAttributes.of(
                (String) properties.get("nickname"),
                (String) properties.get("profile_image"),
                KAKAO_REGISTRATION_ID,
                attributes,
                userNameAttributeName);
    }
}
