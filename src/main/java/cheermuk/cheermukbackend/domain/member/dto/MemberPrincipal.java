package cheermuk.cheermukbackend.domain.member.dto;

import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.domain.member.entity.constants.UserRole;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Builder
public record MemberPrincipal(
        Long id,
        String nickname,
        String imgUrl,
        Collection<? extends GrantedAuthority> authorities,
        Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

    public static MemberPrincipal fromEntity(Member member) {
        return MemberPrincipal.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .imgUrl(member.getImgUrl())
                .authorities(member.getUserRole())
                .build();
    }

    public UserRole getUserRole() {
        return authorities.stream()
                .map(r -> UserRole.valueOf(r.getAuthority().substring(5)))
                .findFirst()
                .orElse(UserRole.ANONYMOUS);
    }

    @Override
    public String getName() {
        return nickname;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class MemberPrincipalBuilder {
        public MemberPrincipalBuilder authorities(UserRole userRole) {
            this.authorities = Set.of(new SimpleGrantedAuthority(userRole.getName()));
            return this;
        }
    }
}
