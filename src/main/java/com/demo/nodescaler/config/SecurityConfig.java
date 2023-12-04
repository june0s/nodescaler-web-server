package com.demo.nodescaler.config;

import com.demo.nodescaler.exceptions.security.CustomAccessDeniedHandler;
import com.demo.nodescaler.exceptions.security.JwtAuthEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // 관리자 기능 추가하려고...
public class SecurityConfig {
//    private CustomUserDetailsService userDetailsService;
//
//    public SecurityConfig(CustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // password 암호화 해준다.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JWTAuthenticationFilter jwtAuthenticationFilter() {
//        return new JWTAuthenticationFilter();
//    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthEntryPoint();
    }

    // CORS 설정을 반드시 해줘야 한다!
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
//            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000")); // ⭐️ 허용할 origin
            config.setAllowedOriginPatterns(Collections.singletonList("*")); // ⭐️ 허용할 origin
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
//                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/auth/**", "/api/cluster/**").permitAll()
                            .requestMatchers("/api/admin/**").authenticated();
                })
//                .formLogin(withDefaults()) // 지금은 사용 안 한다고 함..
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 정책: state less 설정
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 우리가 만든 jwt 필터가 UsernamePass.. 필터 보다 먼저 적용되게
                .exceptionHandling(authManager -> authManager
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .build();
    }
}
