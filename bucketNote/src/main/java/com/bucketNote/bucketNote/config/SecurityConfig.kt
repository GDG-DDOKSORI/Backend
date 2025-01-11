package com.bucketNote.bucketNote.config

import com.bucketNote.bucketNote.jwt.JWTFilter
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class SecurityConfig(
        private val jwtFilter: JWTFilter // Custom JWT Filter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .cors { it.configurationSource(corsConfigurationSource()) } // CORS 설정 추가
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

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf(
                "https://582e-210-94-220-228.ngrok-free.app",
                "http://localhost:3000",
                "https://localhost:3000",
                "https://ddoksori.netlify.app"
        ) // 명시적으로 도메인 설정
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*") // 모든 헤더 허용
        configuration.allowCredentials = true // 인증 정보 포함 허용

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
