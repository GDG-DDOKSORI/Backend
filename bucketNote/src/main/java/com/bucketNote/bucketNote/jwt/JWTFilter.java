package com.bucketNote.bucketNote.jwt;

import com.bucketNote.bucketNote.app.dto.UserCustomDto;
import com.bucketNote.bucketNote.domain.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    public JWTFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return; //조건이 해당되면 메소드 종료(필수)
        }

        String token = authorization.split(" ")[1]; //Bearer 부분 제거 후 순수 토큰만 획득

        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return; // 조건이 해당되면 메소드 종료 (필수)
        }

        Long userId = jwtUtil.getUserId(token);

        User user = User.builder()
                .id(userId)
                .build();

        UserCustomDto userCustomDto = new UserCustomDto(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(userCustomDto, null, userCustomDto.getAuthorities()); //스프링 시큐리티 인증 토큰 생성

        SecurityContextHolder.getContext().setAuthentication(authToken); //세션에 사용자 등록

        filterChain.doFilter(request, response);
    }
}
