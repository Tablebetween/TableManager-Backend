package heesoon.tableManager.Security.Oauth;

import heesoon.tableManager.Member.Service.MemberService;
import heesoon.tableManager.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRequestMapper userRequestMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        //최초 로그인이면 회원가입 처리



        String targetUrl;
        log.info("토큰 발행 시작");

        String token = jwtTokenProvider.generateToken(userDto.getName());
        targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8080/messi")
                //.queryParam("token", token)
                .build().toUriString();

        log.info("@@@@@@@@@@@@@@@@@@@@");
        log.info("target url = {}", targetUrl);
        log.info("token = {}", token);

        getRedirectStrategy().sendRedirect(request,response,targetUrl);

    }
}
