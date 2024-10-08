package cheermuk.cheermukbackend.global.config.security;

import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import cheermuk.cheermukbackend.domain.member.service.MemberService;
import cheermuk.cheermukbackend.global.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String ACCESS_TOKEN_COOKIE = "accessToken";
    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = parseCookieToken(request, ACCESS_TOKEN_COOKIE);
        try {
            if (!StringUtils.hasText(accessToken) || accessToken.equals("null")) {
                filterChain.doFilter(request, response);
                return;
            }
            setAuthentication(request, accessToken);
        } catch (Exception e) {
            log.error("Error occurs during authenticate, {}", e.getMessage());
            throw new AuthException("Failed authenticate");
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, String accessToken) {
        MemberPrincipal memberPrincipal = getMemberPrincipal(accessToken);
        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(
                memberPrincipal, accessToken, memberPrincipal.getAuthorities());
        authenticated.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticated);
    }

    private MemberPrincipal getMemberPrincipal(String accessToken) {
        Long memberId = tokenProvider.getMemberIdFromToken(accessToken);
        return MemberPrincipal.fromEntity(memberService.getMember(memberId));
    }

    private String parseCookieToken(HttpServletRequest request, String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}
