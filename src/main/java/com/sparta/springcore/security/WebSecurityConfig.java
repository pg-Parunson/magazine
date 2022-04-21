package com.sparta.springcore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
    // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    // 회원 관리 처리 API (POST /api/**) 에 대해 CSRF 무시
        http.csrf()
                .ignoringAntMatchers("/api/**");

        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // login 없이 허용
                .antMatchers("/api/register").permitAll()  // 회원가입 페이지 이동
                .antMatchers("/").permitAll()  // 메인 페이지 이동
                .antMatchers("/api/posts").permitAll()  // 게시글 등록, 전체 조회
                .antMatchers("/api/posts/{postNo}").permitAll() // 게시글 삭제, 수정, 조회
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                    // [로그인 기능]
                    .formLogin()
                    // 로그인 View 제공 (GET /user/login)
                    .loginPage("/api/login")
                    // 로그인 처리 (POST /user/login)
                    .loginProcessingUrl("/api/login")
                    // 로그인 처리 후 성공 시 URL
                    .defaultSuccessUrl("/")
                    // 로그인 처리 후 실패 시 URL
                    .failureUrl("/api/login?error")
                    .permitAll()
                .and()
                    // [로그아웃 기능]
                    .logout()
                    // 로그아웃 처리 URL (POST 디폴트, GET 으로 바꾸면 에러가 많다고 함)
                    .logoutUrl("/api/logout")
                    .logoutSuccessUrl("/")
                    .permitAll();
    }
}