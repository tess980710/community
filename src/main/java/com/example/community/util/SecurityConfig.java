package com.example.community.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) // H2 콘솔 허용
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // h2-console 접근 허용
                        .anyRequest().permitAll()  // 모든 요청 허용
                )
                .formLogin(login -> login.disable());  // 로그인 폼 비활성화

        return http.build();
    }
}
