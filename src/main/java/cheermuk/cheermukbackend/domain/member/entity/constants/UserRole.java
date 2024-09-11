package cheermuk.cheermukbackend.domain.member.entity.constants;

import lombok.Getter;

@Getter
public enum UserRole {
    ANONYMOUS("ROLE_ANONYMOUS"),
    MEMBER("ROLE_MEMBER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
