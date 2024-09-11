package cheermuk.cheermukbackend.global.config.security;

import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private static final String TOKEN_COOKIE_NAME = "accessToken";
    private static final int COOKIE_MAX_AGE = 10 * 60; // 10분

    @Value("${oauth2.redirect-uri}")
    private String REDIRECT_URI;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        MemberPrincipal principal = (MemberPrincipal) authentication.getPrincipal();
        // 회원가입되지 않았을 경우 회원가입 처리
        Long memberId = Optional.ofNullable(principal.id()).orElseGet(() -> memberService
                .registerMember(Member.fromPrincipalWithOauth(principal))
                .getId());
        // 토큰 발급
        String accessToken = tokenProvider.generateAccessToken(memberId);
        // 쿠키 추가
        addCookie(response, accessToken);
        // redirect
        response.sendRedirect(REDIRECT_URI);
    }

    private void addCookie(HttpServletResponse response, String cookieValue) {
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, URLEncoder.encode(cookieValue, StandardCharsets.UTF_8));
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
