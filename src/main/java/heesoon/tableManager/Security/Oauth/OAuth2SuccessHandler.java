package heesoon.tableManager.Security.Oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Domain.MemberRole;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.Member.Service.MemberService;
import heesoon.tableManager.Security.JwtTokenProvider;
import heesoon.tableManager.Security.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRequestMapper userRequestMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Token token = null;

        String username = oAuth2User.getName();
        Member findMember = memberRepository.findByUsername(username).orElse(null);
        if (findMember == null) {
            Member createMember = Member.builder()
                    .username(username)
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .nickname(oAuth2User.getAttribute("nickname"))
                    .email(oAuth2User.getAttribute("email"))
                    .pfUrl(oAuth2User.getAttribute("pfUrl"))
                    .sex(oAuth2User.getAttribute("sex"))
                    .birth(oAuth2User.getAttribute("birth"))
                    .role(MemberRole.ROLE_USER)
                    .provider(oAuth2User.getAttribute("provider"))
                    .build();
            memberRepository.save(createMember);
            token = jwtTokenProvider.generateToken(createMember.getMemberId(), createMember.getUsername());
        } else {
            token = jwtTokenProvider.generateToken(findMember.getMemberId(), findMember.getUsername());
        }

        writeTokenResponse(response, token);

        //로그인 완료 후 프론트쪽으로 token 보내기기
        //String url = makeRedirectUrl(token.getToken());
        // getRedirectStrategy().sendRedirect(request, response, url);

    }

    private void writeTokenResponse(HttpServletResponse response, Token token) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("Authorization", token.getToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }

    private String makeRedirectUrl(String token) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect/" + token)
                .build().toUriString();
    }
}
