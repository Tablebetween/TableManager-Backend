package heesoon.tableManager.Security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long tokenPeriod = 1000L * 60L * 60L;
    private static final long refreshTokenPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;

    private final UserDetailsService userDetailsService;

    //객체 초기화
    //secretKey 를 Base64로 인코딩함
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    //JWT 토큰 생성
    public Token generateToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("username", username);

        Date now = new Date();

        return new Token(
                Jwts.builder()
                        .setClaims(claims)  //정보 저장
                        .setIssuedAt(now)   //토근 발행 시간 정보
                        .setExpiration(new Date(now.getTime() + tokenPeriod)) //만료시간
                        .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 Signature에 들어갈 secret 값 세팅
                        .compact(),
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + refreshTokenPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact());

    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //request 의 Header 에서 토큰값을 가져옴 "Authorization" : "TOKEN 값"
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer")) {
            return token.substring(7);
        }

        return token;
    }

    //토큰의 유효성 + 만료일자 확인
    //토큰이 expire 되지 않았는지 true/false 로 반환
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
