package com.example.authserver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "OAuth2 Authorization Server API",
        version = "1.0.0",
        description = "OAuth2 Authorization Server with JWT tokens supporting 15-minute access tokens and refresh tokens",
        contact = @Contact(
            name = "Auth Server Team",
            email = "auth@example.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Development Authorization Server")
    }
)
public class AuthOpenApiConfig {
}