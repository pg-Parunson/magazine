package com.sparta.springcore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(WebSecurity web) {
//    // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 정리
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .httpBasic().disable() // 기본 로그인 페이지 사용 안함
                // REST API 사용하기 때문에 사용 안함
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests() // ↓ 인증 인가 관련 ↓
                    // image 폴더를 login 없이 허용
                    .antMatchers("/images/**").permitAll()
                    // css 폴더를 login 없이 허용
                    .antMatchers("/css/**").permitAll()
                    // 그 외 login 없이 허용하는 범위
                    .antMatchers("/api/register").permitAll()  // 회원가입 페이지 이동
                    .antMatchers("/session-info").permitAll()  // 세션 확인 (개발용)
                    .antMatchers("/").permitAll()  // 메인 페이지 이동
                    .antMatchers("/api/posts").permitAll()  // 게시글 등록, 전체 조회
                    .antMatchers("/api/posts/{postNo}").permitAll() // 게시글 삭제, 수정, 조회
                    .antMatchers("/api/posts/{postNo}/{layout}").permitAll() // 게시글 레이아웃 설정
                    .antMatchers("/api/login").permitAll() // 로그인
                    .antMatchers("/user/kakao/callback").permitAll() // 로그인
                    .antMatchers("/api/logout").permitAll() // 로그아웃
                    .antMatchers("/api/posts/{postNo}/like/{username}").permitAll() // 좋아요
                // 그 외 어떤 요청이든 '인증 필요'
                .anyRequest().authenticated()
                .and()
                    // [로그인 기능]
                    .formLogin().disable();
//                    // 로그인 View 제공 (GET /api/login)
//                    .loginPage("/api/login")
//                    // 로그인 처리 (POST /api/login)
//                    .loginProcessingUrl("/api/login")
//                    // 로그인 처리 후 성공 시 URL
//                    .defaultSuccessUrl("/")
//                    // 로그인 처리 후 실패 시 URL
//                    .failureUrl("/api/login?error")
//                    .permitAll()
//                .and()
//                    // [로그아웃 기능]
//                    .logout()
//                    // 로그아웃 처리 URL (POST 디폴트, GET 으로 바꾸면 에러가 많다고 함)
//                    .logoutUrl("/api/logout")
//                    .logoutSuccessUrl("/")
//                    .permitAll();
//        // 회원 관리 처리 API (POST /api/**) 에 대해 CSRF 무시
//        http.csrf()
//                .ignoringAntMatchers("/api/**");
    }

    // CORS 정책 필터
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}