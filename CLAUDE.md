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
./gradlew jacocoTestReport                        # Generate code coverage report (in build/reports/jacoco-html)
```

### Accessing the Application
- **Base URL**: Application runs on port 8080 by default
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html#/`
- **Actuator endpoints**: Available at `/actuator/*`

## Architecture

### Package Structure
The project uses a package-by-feature structure under `src/main/java/com/replit/example/`:
- **ExampleApplication.java**: Main Spring Boot application entry point
- **Controllers**: REST endpoints (e.g., `HelloController.java`)

**Important**: When creating a new project from this template, update the `maven_group` in `gradle.properties` to your organization's group ID (e.g., `com.yourcompany`). This will require refactoring the package structure and moving files accordingly.

### Key Technologies & Dependencies

**Spring Boot Starters:**
- `spring-boot-starter-web`: REST API support with embedded Tomcat
- `spring-boot-starter-actuator`: Production monitoring and management endpoints
- `spring-boot-starter-data-jpa`: Database persistence (note: no database is currently configured)
- `spring-boot-starter-log4j2`: Logging (Spring Boot's default logging is explicitly excluded)

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
The project uses Log4j2 instead of Spring Boot's default Logback. Note the explicit exclusion in `build.gradle`:
```groovy
configurations {
  all {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
  }
}
```

### Database
The template includes `spring-boot-starter-data-jpa` but no database driver or configuration. When adding database support, you'll need to:
1. Add a database driver dependency (e.g., PostgreSQL, MySQL, H2)
2. Configure datasource in `application.properties`
3. Create entity classes and repositories

## Configuration

All project-level configuration is in `gradle.properties`:
- `maven_group`: Package group ID (default: `com.replit`)
- `app_version`: Application version (default: `0.0.1`)
- `java_version`: Java version (default: `17`)

Runtime configuration goes in `src/main/resources/application.properties` (currently empty).
