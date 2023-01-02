package com.springboot.config.auth;

import com.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 해당 옵션들을 꺼줘야 한다.
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests() // URL별 권한을 설정하는 옵션, antMatchers 앞에 선행되어야 한다
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 해당 URL에 대해 전체 열람 권한을 허용
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 해당 URL에 대해 USER 권한을 가진 사람만 권한을 준다.
                .anyRequest().authenticated() // 설정 이외의 URL들은 로그인한 사용자들에게만 허용한다.
                .and().logout() // 로그아웃 기능에 대한 설정 시작점
                .logoutSuccessUrl("/") // 로그아웃 성공 시 localhost:8080/로 이동
                .and().oauth2Login() // oauth2 로그인 기능에 대한 설정 시작점
                .userInfoEndpoint()  // 사용자 정보를 가져올 때의 설정을 담당
                .userService(customOAuth2UserService); // oauth2 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
