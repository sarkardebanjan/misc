# Pet Management Web Service Module

REST API for pet management with OAuth2 security, JPA/Hibernate, and CRUD operations.

## Module Information
- **Artifact ID**: `app-web-service`
- **Port**: 8081
- **Parent**: `oauth2-microservices`

## Features
- RESTful CRUD API for Pet management
- OAuth2 Resource Server with JWT validation
- JPA/Hibernate with H2 database
- Advanced search and filtering
- Swagger/OpenAPI documentation
- Input validation and error handling
- Pagination and sorting
- Sample data initialization

## Prerequisites
- Auth Server module must be running on port 8080

## Running This Module

### From Root Directory
```bash
mvn spring-boot:run -pl app-web-service
```

### From Module Directory
```bash
cd app-web-service
mvn spring-boot:run
```

### From IntelliJ IDEA
Right-click on `AppWebServiceApplication.java` → Run

## Access Points
- **Server**: http://localhost:8081
- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **H2 Console**: http://localhost:8081/h2-console

## API Endpoints
- `GET /api/pets` - Get all pets (scope: read)
- `POST /api/pets` - Create pet (scope: write)
- `PUT /api/pets/{id}` - Update pet (scope: write)
- `DELETE /api/pets/{id}` - Delete pet (scope: write)
- `GET /api/pets/search?name=X` - Search pets
- `GET /api/pets/type/{type}` - Filter by type
- `GET /api/pets/stats` - Get statistics

## Authentication
Requires OAuth2 JWT tokens with scopes:
- `read` - For GET operations
- `write` - For POST, PUT, DELETE operations

## Sample Data
Includes 8 sample pets for testing:
- Dogs: Buddy, Charlie, Max
- Cats: Whiskers, Luna, Milo  
- Birds: Tweety, Rio
