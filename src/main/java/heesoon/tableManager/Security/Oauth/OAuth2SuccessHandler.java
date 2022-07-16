package heesoon.tableManager.Security.Oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Domain.MemberRole;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.Security.JwtTokenProvider;
import heesoon.tableManager.Security.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
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
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        //최초 로그인이면 회원가입 처리

        String username = oAuth2User.getName();
        if (memberRepository.findByUsername(username).isEmpty()) {
            Member member = Member.builder()
                    .username(username)
                    .password(UUID.randomUUID().toString())
                    .nickname(oAuth2User.getAttribute("nickname"))
                    .email(oAuth2User.getAttribute("email"))
                    .pfUrl(oAuth2User.getAttribute("pfUrl"))
                    .sex(oAuth2User.getAttribute("sex"))
                    .birth(oAuth2User.getAttribute("birth"))
                    .role(MemberRole.ROLE_USER)
                    .build();
            memberRepository.save(member);
        }


        String targetUrl;
        log.info("토큰 발행 시작");

        Token token = jwtTokenProvider.generateToken(userDto.getName());
        log.info("token = {}", token);


//        targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8080/messi")
//                //.queryParam("token", token)
//                .build().toUriString();
//
//        log.info("token = {}", token);
//
//        getRedirectStrategy().sendRedirect(request,response,targetUrl);

        writeTokenResponse(response, token);

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
}
