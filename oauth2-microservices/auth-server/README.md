# OAuth2 Authorization Server Module

OAuth2 Authorization Server with JWT tokens, 15-minute expiry, and refresh token support.

## Module Information
- **Artifact ID**: `auth-server`
- **Port**: 8080  
- **Parent**: `oauth2-microservices`

## Features
- OAuth2 Authorization Server with Spring Security
- JWT Access Tokens (15 minutes expiry)
- Refresh Tokens (7 days expiry)
- Authorization Code + PKCE flow
- OpenID Connect support
- H2 database for development
- Swagger/OpenAPI documentation

## Running This Module

### From Root Directory
```bash
mvn spring-boot:run -pl auth-server
```

### From Module Directory
```bash
cd auth-server
mvn spring-boot:run
```

### From IntelliJ IDEA
Right-click on `AuthServerApplication.java` → Run

## Access Points
- **Server**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console

## Configuration
See `src/main/resources/application.yml` for configuration details.

## OAuth2 Endpoints
- Authorization: `/oauth2/authorize`
- Token: `/oauth2/token`
- JWK Set: `/oauth2/jwks`
- Introspection: `/oauth2/introspect`
- Revocation: `/oauth2/revoke`
