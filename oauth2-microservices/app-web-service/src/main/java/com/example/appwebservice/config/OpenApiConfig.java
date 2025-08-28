package com.example.appwebservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Pet Management API",
        version = "1.0.0",
        description = "A comprehensive API for managing pets with OAuth2 security",
        contact = @Contact(
            name = "Pet Management Team",
            email = "support@petmanagement.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8081", description = "Development Server")
    }
)
@SecurityScheme(
    name = "oauth2",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            authorizationUrl = "http://localhost:8080/oauth2/authorize",
            tokenUrl = "http://localhost:8080/oauth2/token"
        )
    )
)
public class OpenApiConfig {
}