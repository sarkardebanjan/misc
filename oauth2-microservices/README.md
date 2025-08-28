# OAuth2 Microservices - Multi-Module Maven Project

A complete OAuth2 implementation with two Spring Boot microservices in a single Maven multi-module project, perfect for IntelliJ IDEA import.

## 📁 Project Structure

```
oauth2-microservices/
├── pom.xml                    # Parent POM
├── auth-server/               # OAuth2 Authorization Server Module
│   ├── pom.xml               # Child POM
│   └── src/main/java/...     # Auth server source code
├── app-web-service/          # Pet Management API Module  
│   ├── pom.xml               # Child POM
│   └── src/main/java/...     # Web service source code
├── README.md                 # This file
├── start-services.sh         # Start both services
└── stop-services.sh          # Stop both services
```

## 🏗️ Architecture Overview

```
┌─────────────────────┐    OAuth2/JWT    ┌──────────────────────┐
│   Auth Server       │◄─────────────────┤  App Web Service     │
│   (Port 8080)       │                  │  (Port 8081)         │
│                     │                  │                      │
│ ┌─────────────────┐ │                  │ ┌──────────────────┐ │
│ │ Authorization   │ │                  │ │ Pet CRUD API     │ │
│ │ Server          │ │                  │ │ (Resource Server)│ │
│ │ - JWT Tokens    │ │                  │ │ - JPA/Hibernate  │ │
│ │ - 15min expiry  │ │                  │ │ - H2 Database    │ │
│ │ - Refresh Token │ │                  │ │ - REST API       │ │
│ └─────────────────┘ │                  │ └──────────────────┘ │
└─────────────────────┘                  └──────────────────────┘
```

## 🚀 IntelliJ IDEA Import

1. **Open IntelliJ IDEA**
2. **Import Project**: File → Open → Select root `pom.xml`
3. **Import as Maven Project**: Choose "Import Maven project"
4. **Wait for indexing**: Let IntelliJ download dependencies and index
5. **Run Configuration**: Both modules will appear in the project structure

## 💻 Technology Stack

- **Java**: 21 (LTS)
- **Spring Boot**: 3.5.5 (Latest)
- **Maven**: Multi-module setup
- **Spring Security**: OAuth2 Authorization Server
- **Spring Data JPA**: with Hibernate
- **H2 Database**: In-memory for development
- **SpringDoc OpenAPI**: 2.8.11 for Swagger

## ⚡ Quick Start

### Option 1: Using Scripts
```bash
# Start both services
chmod +x start-services.sh
./start-services.sh

# Stop both services  
./stop-services.sh
```

### Option 2: Using Maven
```bash
# From root directory - start both services
mvn spring-boot:run -pl auth-server &
mvn spring-boot:run -pl app-web-service &

# Or run individually
cd auth-server && mvn spring-boot:run
cd app-web-service && mvn spring-boot:run
```

### Option 3: From IntelliJ IDEA
1. **Right-click** on `AuthServerApplication.java` → Run
2. **Right-click** on `AppWebServiceApplication.java` → Run

## 🌐 Access Points

Once both services are running:

- **Auth Server**: http://localhost:8080
  - Swagger UI: http://localhost:8080/swagger-ui.html
  - H2 Console: http://localhost:8080/h2-console

- **Pet Management API**: http://localhost:8081
  - Swagger UI: http://localhost:8081/swagger-ui.html
  - H2 Console: http://localhost:8081/h2-console

## 🔐 Default Configuration

**OAuth2 Client:**
- Client ID: `web-client`
- Client Secret: `secret`
- Grant Types: `authorization_code`, `refresh_token`
- Scopes: `openid`, `profile`, `read`, `write`

**User Credentials:**
- Username: `user`
- Password: `password`

**Token Settings:**
- Access Token: 15 minutes (as requested)
- Refresh Token: 7 days with rotation

## 📋 Module Details

### Auth Server Module
- **Artifact ID**: `auth-server`
- **Port**: 8080
- **Features**: OAuth2 Authorization Server, JWT tokens, 15-min expiry
- **Dependencies**: Spring Security OAuth2 Authorization Server

### App Web Service Module  
- **Artifact ID**: `app-web-service`
- **Port**: 8081
- **Features**: Pet CRUD API, OAuth2 Resource Server, JPA/Hibernate
- **Dependencies**: Spring Security OAuth2 Resource Server

## 🔧 Maven Commands

```bash
# Build entire project
mvn clean install

# Build specific module
mvn clean install -pl auth-server
mvn clean install -pl app-web-service

# Run specific module
mvn spring-boot:run -pl auth-server
mvn spring-boot:run -pl app-web-service

# Package entire project
mvn clean package

# Skip tests
mvn clean install -DskipTests
```

## 🧪 Testing OAuth2 Flow

1. **Get Authorization Code:**
   ```
   GET http://localhost:8080/oauth2/authorize?response_type=code&client_id=web-client&redirect_uri=http://127.0.0.1:8081/login/oauth2/code/web-client&scope=openid%20profile%20read%20write
   ```

2. **Exchange for Tokens:**
   ```bash
   curl -X POST http://localhost:8080/oauth2/token      -H "Content-Type: application/x-www-form-urlencoded"      -u "web-client:secret"      -d "grant_type=authorization_code&code=<code>&redirect_uri=http://127.0.0.1:8081/login/oauth2/code/web-client"
   ```

3. **Use Access Token:**
   ```bash
   curl -H "Authorization: Bearer <access_token>"      http://localhost:8081/api/pets
   ```

4. **Refresh Token:**
   ```bash
   curl -X POST http://localhost:8080/oauth2/token      -H "Content-Type: application/x-www-form-urlencoded"      -u "web-client:secret"      -d "grant_type=refresh_token&refresh_token=<refresh_token>"
   ```

## 📖 API Documentation

### Pet Management API Endpoints
- `GET /api/pets` - Get all pets (scope: read)
- `POST /api/pets` - Create pet (scope: write)
- `PUT /api/pets/{id}` - Update pet (scope: write)
- `DELETE /api/pets/{id}` - Delete pet (scope: write)
- `GET /api/pets/search?name=X` - Search pets
- `GET /api/pets/type/{type}` - Filter by type
- `GET /api/pets/stats` - Get statistics

### Authorization Server Endpoints
- `/oauth2/authorize` - Authorization endpoint
- `/oauth2/token` - Token endpoint
- `/oauth2/jwks` - JWK Set for public keys
- `/api/health` - Health check
- `/api/info` - Server information

## 🔍 IntelliJ IDEA Development

### Project Structure in IDE
```
oauth2-microservices
├── 📁 auth-server
│   ├── 📁 src/main/java
│   │   └── 📁 com.example.authserver
│   │       ├── AuthServerApplication.java
│   │       ├── 📁 config
│   │       └── 📁 controller
│   └── 📁 src/main/resources
└── 📁 app-web-service
    ├── 📁 src/main/java
    │   └── 📁 com.example.appwebservice
    │       ├── AppWebServiceApplication.java
    │       ├── 📁 config
    │       ├── 📁 controller
    │       ├── 📁 model
    │       ├── 📁 repository
    │       └── 📁 service
    └── 📁 src/main/resources
```

### Run Configurations
IntelliJ will automatically detect:
- `AuthServerApplication` - Spring Boot app on port 8080
- `AppWebServiceApplication` - Spring Boot app on port 8081

### Debugging
- Set breakpoints in either module
- Debug configurations are automatically created
- Full hot reload support with Spring Boot DevTools

## 🏭 Production Deployment

For production deployment:
1. **Build JARs**: `mvn clean package`
2. **Deploy separately**: Each module creates its own executable JAR
3. **Configure externally**: Use application-prod.yml files
4. **Use persistent database**: Replace H2 with PostgreSQL/MySQL
5. **Secure secrets**: Use environment variables or secret management

## 📚 Documentation

- [Auth Server Module README](auth-server/README.md)
- [App Web Service Module README](app-web-service/README.md)

## 🤝 Contributing

1. Fork the project
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📝 License

This project is licensed under the MIT License.
