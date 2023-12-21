package com.sniff.springvote18th.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic(HttpBasicConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(httpBasic -> httpBasic.disable()) // http 기본 인증 비활성화
//                .formLogin(formLogin -> formLogin.disable()) // 사용자 지정 로그인 로직 구현
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight Request 허용해주기
//                        .requestMatchers("/api/*/users/**").permitAll()
//                        .requestMatchers(HttpMethod.GET).permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/**").authenticated())
//                        .sessionManagement((sessionManagement) -> sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//                        .build();
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeRequests() // 요청에 대한 접근 권한을 설정하는 메서드
                .requestMatchers("/api/*/users/**").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // Preflight Request 허용해주기

        http
                .csrf(csrf -> csrf.disable())// token을 사용하는 방식이기 때문에 csrf를 disable로 설정
                .formLogin(formLogin -> formLogin.disable()) // 사용자 지정 로그인 로직 구현
                .httpBasic(httpBasic -> httpBasic.disable()); // http 기본 인증 비활성화

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // enable Mysql-console
        http.headers(headers ->
                headers.frameOptions(options ->
                        options.sameOrigin()
                )
        );
        return http.build();


    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("https://react-vote-18th.vercel.app/");
        configuration.addAllowedOrigin("https://react-vote-18th.vercel.app");
        configuration.addAllowedOrigin("http://52.79.115.164:8080");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(Duration.ofSeconds(3600));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
