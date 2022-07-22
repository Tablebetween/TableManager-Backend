package heesoon.tableManager.Token;

import heesoon.tableManager.Security.JwtTokenProvider;
import heesoon.tableManager.Security.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/token/expired")
    public String auth() {
        throw new RuntimeException();
    }

    @GetMapping("/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Refresh");

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            Long userId = jwtTokenProvider.getUserId(token);
            Token newToken = jwtTokenProvider.generateToken(userId, username);

            response.addHeader("Auth", newToken.getToken());
            response.addHeader("Refresh", newToken.getRefreshToken());
            response.setContentType("application/json:charset=UTF-8");

            return "HAPPY NEW TOKEN";
        }

        throw new RuntimeException();
    }

}
