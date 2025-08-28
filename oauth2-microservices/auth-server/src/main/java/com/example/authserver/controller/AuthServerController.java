package com.example.authserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Authorization Server", description = "OAuth2 Authorization Server endpoints")
public class AuthServerController {

    @Operation(summary = "Get server health status", description = "Returns the current status of the authorization server")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Server is running"),
    })
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now(),
            "service", "OAuth2 Authorization Server",
            "version", "1.0.0"
        );
        return ResponseEntity.ok(health);
    }

    @Operation(summary = "Get server information", description = "Returns information about the authorization server")
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> info = Map.of(
            "name", "OAuth2 Authorization Server",
            "version", "1.0.0",
            "description", "Spring Boot OAuth2 Authorization Server with JWT tokens",
            "tokenExpiry", "15 minutes",
            "refreshTokenExpiry", "7 days",
            "supportedGrantTypes", new String[]{"authorization_code", "refresh_token"},
            "endpoints", Map.of(
                "authorize", "/oauth2/authorize",
                "token", "/oauth2/token",
                "jwks", "/oauth2/jwks",
                "introspection", "/oauth2/introspect",
                "revocation", "/oauth2/revoke"
            )
        );
        return ResponseEntity.ok(info);
    }
}