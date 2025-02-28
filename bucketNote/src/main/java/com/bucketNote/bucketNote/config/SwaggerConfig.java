package com.bucketNote.bucketNote.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("bucketnote API Document")
                .version("v0.0.1")
                .description("bucketnote API 명세서입니다.");

        Server httpServer = new Server()
                .url("http://localhost:8080")
                .description("HTTP local server");

        Server httpServer2 = new Server()
                .url("http://test11-server.ap-northeast-2.elasticbeanstalk.com")
                .description("HTTP Prod server");

        Server httpsServer = new Server()
                .url("https://026f-210-94-220-228.ngrok-free.app")
                .description("HTTPS Prod server");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("BearerAuth");

        return new OpenAPI()
                .openapi("3.0.0") // OpenAPI 버전 필드 추가
                .components(new Components().addSecuritySchemes("BearerAuth", securityScheme))
                .info(info)
                .addSecurityItem(securityRequirement)
                .servers(List.of(httpServer,httpServer2,httpsServer));
    }
}
