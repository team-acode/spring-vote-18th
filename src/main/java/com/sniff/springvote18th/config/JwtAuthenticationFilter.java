package com.sniff.springvote18th.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 토큰 꺼내기
        String token = jwtTokenProvider.resolveToken(request);

        // 토큰 존재 여부 + 유효성 확인
        if ( token != null && jwtTokenProvider.validateAccessToken(token)) {
            // token이 존재하고 && 유효하면 (만료되지 않았으면 isExpired가 false)
            System.out.println("[JwtAuthenticationFilter] 토큰 확인하는 부분");
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
