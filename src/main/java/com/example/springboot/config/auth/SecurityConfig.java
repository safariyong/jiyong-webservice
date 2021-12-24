package com.example.springboot.config.auth;

import com.example.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable() // h2-console 화면 사용하기 위해 해당옵션들 disble
                .and().authorizeRequests() //URL별 권한관리 설정.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 관리 지정. URL, HTTP 메소드별로 관리. 위에는 전체 열람권한 줌, 아래는 USER권한 가진사람만 가능
                .anyRequest()// 설정된 값들 이외 나머지 URL 들을 나타냄.
                .authenticated() //나머지 URL들은 모두 인증된 사용자들에게만 허용하게. 즉, 로그인한 사용자들에게 권한부여
                .and().logout().logoutSuccessUrl("/")// 로그아웃 기능에 대한 설정. 로그아웃 성공 시 / 주소로 이동.
                .and().oauth2Login() // OAuth2 로그인 기능 설정.
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정.
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록.
    }
}
