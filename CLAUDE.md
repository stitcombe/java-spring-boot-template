# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java Spring Boot template configured as a REST API gateway. It uses:
- **Java 17** (configurable in `gradle.properties`)
- **Spring Boot 2.7.5** with Spring Cloud 2021.0.4
- **Gradle** for build management

## Essential Commands

### Running the Application
```bash
./gradlew bootRun
```

### Building
```bash
./gradlew build        # Full build with tests
./gradlew clean build  # Clean build
./gradlew clean        # Clear build directory only
```

### Testing
```bash
./gradlew test                                    # Run all tests
./gradlew test --tests ExampleApplicationTests    # Run specific test class
./gradlew test --tests "*HelloController*"        # Run tests matching pattern
./gradlew jacocoTestReport                        # Generate code coverage report (in build/reports/jacoco/html)
./gradlew jacocoTestCoverageVerification          # Verify coverage meets thresholds (70% overall, 60% per class)
./gradlew check                                   # Run tests and coverage verification
```

**Coverage Reports:**
- HTML: `build/reports/jacoco/html/index.html`
- XML: `build/reports/jacoco/jacoco.xml`
- CSV: `build/reports/jacoco/jacoco.csv`

### Accessing the Application
- **Base URL**: Application runs on port 8080 by default
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html#/`
- **Actuator endpoints**: Available at `/actuator/*`

## Architecture

### Package Structure
The project uses a layered architecture under `src/main/java/com/replit/example/`:
- **ExampleApplication.java**: Main Spring Boot application entry point
- **Controllers**: REST endpoints (e.g., `HelloController.java`)
- **config/**: Configuration classes
  - `WebConfig.java`: CORS and web configuration
  - `OpenApiConfig.java`: Swagger/OpenAPI documentation configuration
- **dto/**: Data Transfer Objects
  - `ErrorResponse.java`: Standardized error response format
- **exception/**: Exception handling
  - `GlobalExceptionHandler.java`: Centralized exception handling with @RestControllerAdvice

Test structure under `src/test/java/com/replit/example/`:
- **Unit tests**: Individual class tests (e.g., `GlobalExceptionHandlerTest.java`, `ErrorResponseTest.java`)
- **integration/**: Integration tests with full Spring context
- **util/**: Test utilities (e.g., `TestUtils.java` for JSON conversion)

**Important**: When creating a new project from this template, update the `maven_group` in `gradle.properties` to your organization's group ID (e.g., `com.yourcompany`). This will require refactoring the package structure and moving files accordingly.

### Key Technologies & Dependencies

**Spring Boot Starters:**
- `spring-boot-starter-web`: REST API support with embedded Tomcat
- `spring-boot-starter-actuator`: Production monitoring and management endpoints
- `spring-boot-starter-validation`: Bean validation with Hibernate Validator
- `spring-boot-starter-log4j2`: Logging (Spring Boot's default Logback is explicitly excluded)

**Additional Libraries:**
- `spring-cloud-starter-sleuth`: Distributed tracing with automatic trace/span ID injection
- `springdoc-openapi-ui`: Automatic OpenAPI 3.0 documentation and Swagger UI
- `modelmapper`: Object mapping between DTOs and entities
- `lombok`: Reduces boilerplate (getters, setters, constructors, etc.)

**Testing:**
- JUnit 5 (Jupiter)
- Spring Boot Test with MockMvc support
- Jacoco for code coverage

### Logging Configuration
The project uses Log4j2 instead of Spring Boot's default Logback. Configuration is in `src/main/resources/log4j2-spring.xml`. Note the explicit exclusion of default logging in `build.gradle`:
```groovy
configurations {
  all {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
  }
}
```

### Database
This template does not include database dependencies by default. When adding database support, you'll need to:
1. Add `spring-boot-starter-data-jpa` to `build.gradle`
2. Add a database driver dependency (e.g., PostgreSQL, MySQL, H2)
3. Configure datasource in `application.properties`
4. Create entity classes and repositories

### Exception Handling
Global exception handling is implemented via `GlobalExceptionHandler` using `@RestControllerAdvice`:
- **Validation errors** (`MethodArgumentNotValidException`): Returns 400 with field-level error details
- **Illegal arguments** (`IllegalArgumentException`): Returns 400 with error message
- **Generic exceptions**: Returns 500 with sanitized error message
- All errors return a standardized `ErrorResponse` format with message, details, and timestamp

### CORS Configuration
CORS is configured in `WebConfig` with the following defaults:
- Allowed origins: `http://localhost:3000`, `http://localhost:8080`
- Allowed methods: GET, POST, PUT, DELETE, OPTIONS, PATCH
- Credentials support enabled
- Applies to `/api/**` endpoints

Modify `WebConfig.java` to customize CORS settings for your environment.

### Testing
The project includes comprehensive testing infrastructure:

**Test Structure:**
- **Unit tests**: Test individual classes in isolation (e.g., `GlobalExceptionHandlerTest`, `ErrorResponseTest`)
- **Integration tests**: Test with full Spring context using `@SpringBootTest` and `MockMvc` (e.g., `HelloControllerTest`, `ActuatorEndpointsTest`)
- **Test utilities**: Helper classes for common test operations (`TestUtils`)

**Test Coverage:**
- Jacoco configured with coverage thresholds:
  - **70% overall** coverage minimum
  - **60% per-class** coverage minimum
  - Excludes config classes, DTOs, and main application class
- Tests automatically run coverage reports via `finalizedBy jacocoTestReport`
- Coverage reports generated in multiple formats (HTML, XML, CSV)

**Example Test Patterns:**
```java
// Controller integration test
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnGreeting() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Greetings")));
    }
}
```

## Configuration

**Project-level configuration** is in `gradle.properties`:
- `maven_group`: Package group ID (default: `com.replit`)
- `app_version`: Application version (default: `0.0.1`)
- `java_version`: Java version (default: `17`)

**Runtime configuration** is in `src/main/resources/application.properties` with the following defaults:
- Application name: `example-service`
- Server port: `8080`
- Actuator endpoints: health, info, metrics, prometheus
- OpenAPI docs: `/api-docs` and `/swagger-ui.html`
- Jackson configured for ISO date formats and null exclusion
- Logging levels configured for development

**Environment profiles** are available:
- `application-dev.properties`: Development profile with verbose logging and detailed error messages
- `application-prod.properties`: Production profile with minimal logging and no stack traces

To activate a profile, use: `./gradlew bootRun --args='--spring.profiles.active=dev'`
