package heesoon.tableManager.Config;

import heesoon.tableManager.Security.JwtAuthenticationFilter;
import heesoon.tableManager.Security.JwtTokenProvider;
import heesoon.tableManager.Security.Oauth.CustomOAuth2UserService;
import heesoon.tableManager.Security.Oauth.OAuth2SuccessHandler;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.hibernate.criterion.Restrictions.and;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final OAuth2SuccessHandler successHandler;
    private final CustomOAuth2UserService oAuth2UserService;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HttpSecurity 객체 설정
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                //토큰 기반 인증이므로 세션 사용 X
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //개발 편의상 현재까지는 모두 permit
                //api/user/** 주소는 ROLE_USER 또는 ROLE_ADMIN 권한만 접근 가능
                //.antMatchers("/board/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/token/**").permitAll()  //토큰이 만료되어 인증을 하지 못하면 /token/expired 로 리다이렉트하여 Refresh 요청을 해야한다는 것을 알려주고 Refresh 할 수 있도록 /token/** 을 전체 허용
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/login/**").permitAll()
                //.antMatchers("/board/**").permitAll()
                //다른 요청은 누구든지 접근 가능
                .anyRequest().authenticated()
                //.anyRequest().permitAll()
                .and()
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint().userService(oAuth2UserService);
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
