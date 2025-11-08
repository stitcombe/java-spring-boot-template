# Java Spring Boot Template

A production-ready Spring Boot 3 template for building REST API services with comprehensive testing, monitoring, and DevOps support.

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Features

### Core Capabilities
- ✅ **Spring Boot 3.3.5** with Java 21 LTS
- ✅ **REST API** with Spring Web MVC
- ✅ **OpenAPI 3.0** documentation with Swagger UI
- ✅ **Production monitoring** with Spring Boot Actuator
- ✅ **Distributed tracing** with Micrometer Tracing
- ✅ **Request validation** with Hibernate Validator
- ✅ **Global exception handling** with standardized error responses
- ✅ **CORS configuration** for cross-origin requests

### Developer Experience
- ✅ **Gradle** build system with wrapper
- ✅ **Lombok** to reduce boilerplate code
- ✅ **ModelMapper** for object mapping
- ✅ **Log4j2** for flexible logging
- ✅ **Hot reload** support for development

### Testing & Quality
- ✅ **JUnit 5** for unit and integration testing
- ✅ **MockMvc** for controller testing
- ✅ **Jacoco** code coverage with 70% threshold
- ✅ **Comprehensive test utilities** and examples
- ✅ **Multiple environment profiles** (dev, prod)

### DevOps
- ✅ **Docker** support with multi-stage builds
- ✅ **Docker Compose** for local development
- ✅ **GitHub Actions** CI/CD pipeline
- ✅ **Health checks** and monitoring endpoints
- ✅ **Non-root container** execution

## Prerequisites

- **Java 21 LTS** or higher ([Download](https://adoptium.net/))
- **Docker** (optional, for containerized deployment)
- **Git** for version control

## Quick Start

### 1. Clone or Use as Template

```bash
# Clone this repository
git clone https://github.com/yourusername/java-spring-boot-template.git
cd java-spring-boot-template

# Or use GitHub's "Use this template" button
```

### 2. Configure Your Project

Edit `gradle.properties`:
```properties
maven_group=com.yourcompany
app_version=0.0.1
java_version=21
```

### 3. Update Package Structure

Refactor the package from `com.replit.example` to your `maven_group` (e.g., `com.yourcompany.yourapp`):
- Update package declarations in all Java files
- Move files to the new package directory structure
- Update imports across the codebase

### 4. Run the Application

```bash
# Using Gradle wrapper
./gradlew bootRun

# Or with a specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'
```

The application will start on `http://localhost:8080`

### 5. Verify Installation

- **API Endpoint**: http://localhost:8080/
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health
- **Metrics**: http://localhost:8080/actuator/metrics

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/com/replit/example/
│   │   │   ├── ExampleApplication.java       # Main application class
│   │   │   ├── HelloController.java           # Example REST controller
│   │   │   ├── config/                        # Configuration classes
│   │   │   │   ├── OpenApiConfig.java         # Swagger/OpenAPI config
│   │   │   │   └── WebConfig.java             # CORS and web config
│   │   │   ├── dto/                           # Data Transfer Objects
│   │   │   │   └── ErrorResponse.java         # Standardized error format
│   │   │   └── exception/                     # Exception handling
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties         # Default configuration
│   │       ├── application-dev.properties     # Development profile
│   │       ├── application-prod.properties    # Production profile
│   │       └── log4j2-spring.xml             # Logging configuration
│   └── test/
│       └── java/com/replit/example/
│           ├── integration/                   # Integration tests
│           ├── util/                          # Test utilities
│           └── *Test.java                     # Unit tests
├── build.gradle                               # Gradle build configuration
├── gradle.properties                          # Project properties
├── Dockerfile                                 # Docker image definition
├── docker-compose.yml                         # Docker Compose setup
└── .github/workflows/ci.yml                  # CI/CD pipeline
```

## Development Guide

### Building the Project

```bash
# Full build with tests
./gradlew build

# Clean build
./gradlew clean build

# Build without tests (not recommended)
./gradlew build -x test
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests ExampleApplicationTests

# Run tests matching pattern
./gradlew test --tests "*HelloController*"

# Run specific test method
./gradlew test --tests HelloControllerTest.shouldReturnGreeting

# Continuous testing (watches for changes)
./gradlew test --continuous

# Force re-run all tests
./gradlew cleanTest test
```

### Code Coverage

```bash
# Generate coverage report
./gradlew jacocoTestReport

# Verify coverage thresholds (70% overall, 60% per class)
./gradlew jacocoTestCoverageVerification

# Run tests with coverage verification
./gradlew check
```

**View Reports:**
- HTML: `build/reports/jacoco/html/index.html`
- XML: `build/reports/jacoco/jacoco.xml`
- CSV: `build/reports/jacoco/jacoco.csv`

### Gradle Commands Reference

| Command | Description |
|---------|-------------|
| `./gradlew bootRun` | Run the application |
| `./gradlew build` | Build the project |
| `./gradlew test` | Run tests |
| `./gradlew clean` | Clean build directory |
| `./gradlew check` | Run tests and coverage verification |
| `./gradlew dependencies` | View dependency tree |
| `./gradlew tasks` | List all available tasks |

## Configuration

### Application Properties

Configuration is managed through Spring Boot properties files:

**`application.properties`** - Base configuration
```properties
spring.application.name=example-service
server.port=8080
management.endpoints.web.exposure.include=health,info,metrics,prometheus
```

**`application-dev.properties`** - Development settings
- Verbose logging
- Detailed error messages
- Debug-friendly configuration

**`application-prod.properties`** - Production settings
- Minimal logging
- No stack traces in responses
- Production-optimized settings

### Activating Profiles

```bash
# Via Gradle
./gradlew bootRun --args='--spring.profiles.active=dev'

# Via environment variable
export SPRING_PROFILES_ACTIVE=dev
./gradlew bootRun

# Via Docker
docker run -e SPRING_PROFILES_ACTIVE=prod example-service
```

### CORS Configuration

CORS is configured in `WebConfig.java`:
```java
registry.addMapping("/api/**")
    .allowedOrigins("http://localhost:3000", "http://localhost:8080")
    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
    .allowCredentials(true);
```

Modify these settings based on your deployment environment.

## API Documentation

### Swagger UI

Access interactive API documentation at:
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI Specification

Raw OpenAPI 3.0 JSON available at:
```
http://localhost:8080/api-docs
```

### Example Endpoint

```bash
# Hello endpoint
curl http://localhost:8080/

# Response
{
  "id": 1,
  "content": "Greetings from Spring Boot!"
}
```

## Monitoring & Observability

### Actuator Endpoints

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Application health status |
| `/actuator/info` | Application information |
| `/actuator/metrics` | Application metrics |
| `/actuator/prometheus` | Prometheus-formatted metrics |

### Distributed Tracing

The application includes Micrometer Tracing with Brave, which automatically:
- Generates trace and span IDs for each request
- Propagates trace context across service boundaries
- Logs trace IDs for correlation

### Logging

Logs are configured using Log4j2 (`src/main/resources/log4j2-spring.xml`):
```xml
<PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5level [%t] %c{1} - %msg%n"/>
```

## Docker

### Building Docker Image

```bash
# Build image
docker build -t example-service .

# Build with custom tag
docker build -t mycompany/myservice:1.0.0 .
```

### Running with Docker

```bash
# Run container
docker run -p 8080:8080 example-service

# Run with environment variables
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  -e JAVA_OPTS="-Xmx512m" \
  example-service

# Run in detached mode
docker run -d -p 8080:8080 --name my-service example-service
```

### Docker Compose

Start all services with Docker Compose:

```bash
# Start services
docker-compose up

# Start in detached mode
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop services
docker-compose down

# Rebuild and start
docker-compose up --build
```

The `docker-compose.yml` includes:
- Application service with health checks
- Network configuration
- PostgreSQL template (commented out, ready to enable)

### Docker Features

- **Multi-stage build** for optimized image size
- **Non-root user** for enhanced security
- **Health checks** for container orchestration
- **Alpine-based images** for minimal footprint
- **Layer caching** for faster builds

## CI/CD

### GitHub Actions

The project includes a complete CI/CD pipeline (`.github/workflows/ci.yml`):

**Build & Test Job:**
- ✅ Runs on push to `main`/`develop` and pull requests
- ✅ Sets up Java 21 with Temurin distribution
- ✅ Executes full build with tests
- ✅ Generates and verifies code coverage
- ✅ Uploads coverage reports as artifacts
- ✅ Publishes test results to PR

**Docker Build Job:**
- ✅ Builds Docker image after successful tests
- ✅ Uses Docker Buildx for multi-platform support
- ✅ Implements layer caching
- ✅ Template for Docker Hub publishing (commented)

### Enabling Docker Hub Push

1. Add secrets to your GitHub repository:
   - `DOCKER_USERNAME`
   - `DOCKER_PASSWORD`

2. Uncomment Docker Hub push steps in `.github/workflows/ci.yml`

3. Update image name in workflow

## Testing

### Test Architecture

```
src/test/java/
├── integration/              # Integration tests with full Spring context
│   └── ActuatorEndpointsTest.java
├── util/                     # Test utilities
│   └── TestUtils.java       # JSON conversion helpers
├── ExampleApplicationTests.java
├── HelloControllerTest.java
├── ErrorResponseTest.java
└── GlobalExceptionHandlerTest.java
```

### Test Types

**Unit Tests** - Test individual classes in isolation
```java
@Test
void shouldCreateErrorResponse() {
    ErrorResponse error = new ErrorResponse("Error", "Details");
    assertNotNull(error.getTimestamp());
}
```

**Integration Tests** - Test with full Spring context
```java
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnGreeting() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }
}
```

### Coverage Thresholds

- **Overall coverage**: 70% minimum
- **Per-class coverage**: 60% minimum
- **Exclusions**: Config classes, DTOs, main application class

## Exception Handling

Global exception handling is implemented via `@RestControllerAdvice`:

| Exception | HTTP Status | Response |
|-----------|-------------|----------|
| `MethodArgumentNotValidException` | 400 | Field-level validation errors |
| `IllegalArgumentException` | 400 | Error message with details |
| Generic exceptions | 500 | Sanitized error message |

**Error Response Format:**
```json
{
  "message": "Validation failed",
  "details": "Field 'email' must be a valid email address",
  "timestamp": "2024-01-15T10:30:00"
}
```

## Adding Database Support

This template doesn't include database dependencies by default. To add database support:

### 1. Add Dependencies

Edit `build.gradle`:
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'org.postgresql:postgresql'  // or your database driver
}
```

### 2. Configure Data Source

Edit `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=youruser
spring.datasource.password=yourpass
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```

### 3. Enable PostgreSQL in Docker Compose

Uncomment the `postgres` service in `docker-compose.yml` and volumes section.

### 4. Create Entities and Repositories

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // getters, setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

## Troubleshooting

### Common Issues

**Port already in use:**
```bash
# Change port temporarily
./gradlew bootRun --args='--server.port=8081'

# Or update application.properties
server.port=8081
```

**Build failures:**
```bash
# Clean and rebuild
./gradlew clean build

# View dependency tree
./gradlew dependencies

# Check for dependency conflicts
./gradlew dependencyInsight --dependency spring-boot-starter-web
```

**Tests failing:**
```bash
# Run specific test with debug output
./gradlew test --tests ClassName.methodName --info

# Clean test cache
./gradlew cleanTest test
```

**Coverage verification failures:**
```bash
# View HTML coverage report
open build/reports/jacoco/html/index.html

# Temporarily disable (not recommended)
# Comment out in build.gradle:
# check { dependsOn jacocoTestCoverageVerification }
```

**Gradle wrapper issues:**
```bash
# Regenerate wrapper
gradle wrapper --gradle-version 8.10
```

## Customization Checklist

When creating a new project from this template:

- [ ] Update `maven_group` in `gradle.properties`
- [ ] Refactor package from `com.replit.example` to your organization
- [ ] Update `spring.application.name` in `application.properties`
- [ ] Modify CORS origins in `WebConfig.java`
- [ ] Update Docker image name in `Dockerfile` and `docker-compose.yml`
- [ ] Configure CI/CD workflow for your repository
- [ ] Update OpenAPI info in `OpenApiConfig.java`
- [ ] Add your license file
- [ ] Update this README with your project details

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 LTS | Programming language |
| Spring Boot | 3.3.5 | Application framework |
| Spring Cloud | 2023.0.3 | Cloud-native patterns |
| Gradle | 8.10 | Build automation |
| Lombok | Latest | Boilerplate reduction |
| ModelMapper | 3.2.1 | Object mapping |
| Springdoc OpenAPI | 2.6.0 | API documentation |
| Micrometer Tracing | Latest | Distributed tracing |
| JUnit 5 | Latest | Testing framework |
| Jacoco | 0.8.11 | Code coverage |
| Log4j2 | Latest | Logging |

## Best Practices

### Security
- ✅ Non-root Docker user
- ✅ No sensitive data in properties files
- ✅ CORS properly configured
- ✅ Exception messages sanitized

### Code Quality
- ✅ 70% test coverage requirement
- ✅ Integration and unit tests
- ✅ Lombok for clean code
- ✅ Standardized error responses

### DevOps
- ✅ Multi-stage Docker builds
- ✅ Health check endpoints
- ✅ Environment-based configuration
- ✅ CI/CD pipeline included

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

- **Documentation**: See [CLAUDE.md](CLAUDE.md) for detailed development guidelines
- **Issues**: Report bugs via GitHub Issues
- **Discussions**: Use GitHub Discussions for questions

## Acknowledgments

- Spring Boot team for the excellent framework
- The Java community for continuous support
- Contributors to all the libraries used in this template

---

**Made with ☕ and Spring Boot**
