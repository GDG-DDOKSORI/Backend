package com.bucketNote.bucketNote.config

import com.bucketNote.bucketNote.jwt.JWTFilter
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
        private val jwtFilter: JWTFilter // Custom JWT Filter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf { it.disable() } // CSRF 비활성화
                .authorizeHttpRequests { auth ->
                    auth
                            .requestMatchers(
                                    "/v3/api-docs/**",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html",
                                    "/api/v1/users",
                                    "/api/kakao/**" // 카카오 API 허용
                            ).permitAll() // 특정 경로는 인증 없이 접근 가능
                            .anyRequest().authenticated() // 나머지는 인증 필요
                }
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java) // JWT 필터 추가
                .exceptionHandling { exceptions ->
                    exceptions.authenticationEntryPoint { _, response, _ ->
                        // 인증 실패 시 401 응답 처리
                        response.status = HttpServletResponse.SC_UNAUTHORIZED
                        response.writer.write("Unauthorized")
                    }
                }
        return http.build()
    }
}
