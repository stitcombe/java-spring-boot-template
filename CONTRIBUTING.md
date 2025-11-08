# Contributing to Java Spring Boot Template

Thank you for considering contributing to this Spring Boot template! This document provides guidelines and instructions for contributing to the project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Setup](#development-setup)
- [Development Workflow](#development-workflow)
- [Coding Standards](#coding-standards)
- [Testing Guidelines](#testing-guidelines)
- [Commit Message Guidelines](#commit-message-guidelines)
- [Pull Request Process](#pull-request-process)
- [Code Review Process](#code-review-process)
- [Documentation](#documentation)
- [Community](#community)

## Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inspiring community for all. Please be respectful and constructive in your interactions.

### Our Standards

**Positive behaviors include:**
- Using welcoming and inclusive language
- Being respectful of differing viewpoints and experiences
- Gracefully accepting constructive criticism
- Focusing on what is best for the community
- Showing empathy towards other community members

**Unacceptable behaviors include:**
- Trolling, insulting/derogatory comments, and personal attacks
- Public or private harassment
- Publishing others' private information without permission
- Other conduct which could reasonably be considered inappropriate

## Getting Started

### Prerequisites

Before you begin, ensure you have:
- **Java 21 LTS** installed ([Download](https://adoptium.net/))
- **Git** for version control
- **Docker** (optional, for testing containerization)
- A **GitHub account**

### First-Time Contributors

If you're new to contributing to open source:
1. Look for issues labeled `good-first-issue` or `help-wanted`
2. Read through this entire document
3. Set up your development environment (see [Development Setup](#development-setup))
4. Don't hesitate to ask questions in issue comments!

## How Can I Contribute?

### Reporting Bugs

Before creating a bug report:
- **Search existing issues** to avoid duplicates
- **Check if the issue has been fixed** in the latest version
- **Collect information** about the bug

**When creating a bug report, include:**
- **Clear and descriptive title**
- **Exact steps to reproduce** the problem
- **Expected behavior** and **actual behavior**
- **Java version** and **Spring Boot version**
- **Operating system** and version
- **Stack traces** or error messages (if applicable)
- **Code samples** or test cases demonstrating the issue

**Bug report template:**
```markdown
## Bug Description
A clear and concise description of the bug.

## Steps to Reproduce
1. Go to '...'
2. Execute '...'
3. See error

## Expected Behavior
What you expected to happen.

## Actual Behavior
What actually happened.

## Environment
- Java Version: [e.g., 21]
- Spring Boot Version: [e.g., 3.3.5]
- OS: [e.g., Ubuntu 22.04]
- Gradle Version: [e.g., 8.10]

## Additional Context
Add any other context, screenshots, or logs.
```

### Suggesting Features

Before suggesting a feature:
- **Check the roadmap** and existing feature requests
- **Ensure it aligns** with the project's goals
- **Provide a clear use case**

**When suggesting a feature, include:**
- **Clear and descriptive title**
- **Detailed description** of the proposed feature
- **Use cases** and benefits
- **Possible implementation** approach
- **Alternatives considered**

**Feature request template:**
```markdown
## Feature Description
A clear and concise description of the feature.

## Use Case
Describe the problem this feature would solve.

## Proposed Solution
How should this feature work?

## Alternatives Considered
What other solutions did you consider?

## Additional Context
Any other information, mockups, or examples.
```

### Improving Documentation

Documentation improvements are always welcome:
- Fix typos or clarify existing documentation
- Add missing documentation
- Improve code examples
- Update outdated information
- Translate documentation (if applicable)

## Development Setup

### 1. Fork and Clone

```bash
# Fork the repository on GitHub, then clone your fork
git clone https://github.com/YOUR_USERNAME/java-spring-boot-template.git
cd java-spring-boot-template

# Add upstream remote
git remote add upstream https://github.com/stitcombe/java-spring-boot-template.git
```

### 2. Verify Setup

```bash
# Run tests to ensure everything works
./gradlew clean build

# Run the application
./gradlew bootRun
```

### 3. Create a Branch

```bash
# Update your main branch
git checkout main
git pull upstream main

# Create a feature branch
git checkout -b feature/your-feature-name
# Or for bug fixes
git checkout -b fix/bug-description
```

## Development Workflow

### Branch Naming Convention

Use descriptive branch names with prefixes:
- `feature/` - New features (e.g., `feature/add-authentication`)
- `fix/` - Bug fixes (e.g., `fix/null-pointer-exception`)
- `docs/` - Documentation changes (e.g., `docs/update-readme`)
- `refactor/` - Code refactoring (e.g., `refactor/improve-error-handling`)
- `test/` - Adding or updating tests (e.g., `test/add-integration-tests`)
- `chore/` - Maintenance tasks (e.g., `chore/update-dependencies`)

### Development Cycle

1. **Create a branch** from `main`
2. **Make your changes** following coding standards
3. **Write tests** for your changes
4. **Run tests** locally to ensure they pass
5. **Commit your changes** with clear messages
6. **Push to your fork**
7. **Create a pull request**

### Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests HelloControllerTest

# Run tests with coverage
./gradlew clean test jacocoTestReport

# Verify coverage thresholds
./gradlew jacocoTestCoverageVerification

# Run full check (tests + coverage verification)
./gradlew check
```

### Local Testing

Before submitting a PR:

```bash
# Clean build
./gradlew clean build

# Run all tests
./gradlew test

# Verify code coverage
./gradlew check

# Test Docker build (optional but recommended)
docker build -t example-service .
docker run -p 8080:8080 example-service

# In another terminal, verify endpoints
curl http://localhost:8080/actuator/health
curl http://localhost:8080/
```

## Coding Standards

### Java Code Style

Follow these Java coding conventions:

**Formatting:**
- Use **4 spaces** for indentation (no tabs)
- Maximum line length: **120 characters**
- Use **Java naming conventions**:
  - Classes: `PascalCase`
  - Methods/Variables: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
  - Packages: `lowercase`

**Best Practices:**
- Use **Lombok** annotations to reduce boilerplate
- Follow **SOLID principles**
- Write **self-documenting code** with clear names
- Add **JavaDoc** for public APIs
- Use **meaningful variable names**
- Keep methods **small and focused** (Single Responsibility)

**Example:**
```java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId the unique identifier of the user
     * @return the user entity
     * @throws UserNotFoundException if user is not found
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
```

### Spring Boot Best Practices

- Use **constructor injection** (prefer `@RequiredArgsConstructor` over `@Autowired`)
- Implement **proper exception handling** using `@RestControllerAdvice`
- Use **DTOs** for API requests/responses
- Follow **REST conventions** for endpoints
- Use **appropriate HTTP status codes**
- Implement **request validation** using Bean Validation
- Use **profiles** for environment-specific configuration

### Code Organization

```
src/main/java/com/yourcompany/yourapp/
├── YourApplication.java          # Main class
├── controller/                   # REST controllers
├── service/                      # Business logic
├── repository/                   # Data access
├── model/                        # Domain entities
├── dto/                          # Data Transfer Objects
├── config/                       # Configuration classes
├── exception/                    # Custom exceptions
└── util/                         # Utility classes
```

### Lombok Usage

Use Lombok appropriately:
- `@Data` - For simple DTOs
- `@Getter/@Setter` - When you need more control
- `@RequiredArgsConstructor` - For dependency injection
- `@Builder` - For complex object construction
- `@Slf4j` - For logging
- **Avoid** `@Data` on entities (can cause issues with JPA)

## Testing Guidelines

### Testing Requirements

**All contributions must include tests:**
- **Unit tests** for business logic
- **Integration tests** for API endpoints
- **Test coverage** must meet thresholds (70% overall, 60% per class)

### Test Structure

```
src/test/java/
├── integration/              # Integration tests
│   └── *IntegrationTest.java
├── util/                     # Test utilities
│   └── TestUtils.java
└── *Test.java               # Unit tests
```

### Writing Unit Tests

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Should return user when user exists")
    void shouldReturnUserWhenUserExists() {
        // Given
        Long userId = 1L;
        User expectedUser = new User(userId, "John Doe");
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // When
        User actualUser = userService.getUserById(userId);

        // Then
        assertThat(actualUser).isEqualTo(expectedUser);
        verify(userRepository).findById(userId);
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void shouldThrowExceptionWhenUserNotFound() {
        // Given
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundException.class,
            () -> userService.getUserById(userId));
    }
}
```

### Writing Integration Tests

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/users/{id} should return user")
    void shouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").exists());
    }

    @Test
    @DisplayName("GET /api/users/{id} should return 404 for non-existent user")
    void shouldReturn404ForNonExistentUser() throws Exception {
        mockMvc.perform(get("/api/users/999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").exists());
    }
}
```

### Test Naming Convention

Use descriptive test names:
- **Method name pattern**: `should[ExpectedBehavior]When[StateUnderTest]`
- **Display names**: Use `@DisplayName` for readable descriptions

**Examples:**
```java
@Test
@DisplayName("Should create user when valid data is provided")
void shouldCreateUserWhenValidDataProvided() { }

@Test
@DisplayName("Should return 400 when email is invalid")
void shouldReturn400WhenEmailIsInvalid() { }
```

## Commit Message Guidelines

### Commit Message Format

Follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Commit Types

- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting, missing semi-colons, etc.)
- **refactor**: Code refactoring without feature changes
- **perf**: Performance improvements
- **test**: Adding or updating tests
- **chore**: Maintenance tasks, dependency updates
- **ci**: CI/CD configuration changes

### Commit Examples

**Good commits:**
```bash
feat(user): add user registration endpoint

Implement POST /api/users endpoint with email validation
and password encryption. Includes integration tests and
proper error handling.

Closes #123

---

fix(auth): resolve null pointer exception in token validation

Add null check before accessing token claims to prevent NPE
when token is malformed.

Fixes #456

---

docs(readme): update Docker installation instructions

Clarify Docker Compose usage and add troubleshooting section
for common container issues.

---

test(controller): add integration tests for error responses

Add test coverage for 400 and 500 error scenarios in
HelloController.

---

chore(deps): upgrade Spring Boot to 3.3.6

Update Spring Boot version and related dependencies.
All tests passing.
```

**Bad commits (avoid):**
```bash
# Too vague
fix: bug fix

# No description
feat: new feature

# Not descriptive
update files

# Multiple concerns in one commit
feat: add user endpoint and fix bug and update docs
```

### Commit Guidelines

- **One logical change per commit**
- **Write in imperative mood** ("add feature" not "added feature")
- **Limit subject line to 50 characters**
- **Capitalize subject line**
- **Don't end subject line with a period**
- **Wrap body at 72 characters**
- **Explain what and why, not how**
- **Reference issues** in the footer

## Pull Request Process

### Before Creating a PR

Checklist before submitting:

- [ ] Code follows the project's coding standards
- [ ] All tests pass locally (`./gradlew test`)
- [ ] Code coverage meets thresholds (`./gradlew check`)
- [ ] New tests are added for new functionality
- [ ] Documentation is updated (if applicable)
- [ ] Commit messages follow guidelines
- [ ] Branch is up to date with `main`
- [ ] No merge conflicts

### Creating a Pull Request

1. **Push your branch** to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create PR** on GitHub with a clear title and description

3. **Fill out the PR template** completely

4. **Link related issues** using keywords (Closes #123, Fixes #456)

### Pull Request Template

```markdown
## Description
Brief description of the changes.

## Type of Change
- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] Documentation update

## Related Issues
Closes #(issue number)

## Changes Made
- Change 1
- Change 2
- Change 3

## Testing
Describe the tests you ran and how to reproduce them:
- [ ] Unit tests pass
- [ ] Integration tests pass
- [ ] Manual testing performed

## Checklist
- [ ] My code follows the style guidelines of this project
- [ ] I have performed a self-review of my own code
- [ ] I have commented my code, particularly in hard-to-understand areas
- [ ] I have made corresponding changes to the documentation
- [ ] My changes generate no new warnings
- [ ] I have added tests that prove my fix is effective or that my feature works
- [ ] New and existing unit tests pass locally with my changes
- [ ] Any dependent changes have been merged and published

## Screenshots (if applicable)
Add screenshots to demonstrate the changes.

## Additional Notes
Any additional information that reviewers should know.
```

### PR Title Guidelines

Use clear, descriptive titles:
- ✅ `feat: add user authentication with JWT`
- ✅ `fix: resolve memory leak in connection pool`
- ✅ `docs: update API documentation for v2 endpoints`
- ❌ `Update`
- ❌ `Fix bug`
- ❌ `Changes`

## Code Review Process

### For Contributors

**After submitting a PR:**
- Respond to feedback promptly
- Be open to suggestions and criticism
- Make requested changes in new commits (don't force-push)
- Mark conversations as resolved when addressed
- Be patient - reviews may take a few days

**During review:**
- Explain your reasoning for design decisions
- Ask questions if feedback is unclear
- Don't take criticism personally
- Thank reviewers for their time

### For Reviewers

**When reviewing PRs:**
- Be respectful and constructive
- Explain the reasoning behind suggestions
- Distinguish between required changes and suggestions
- Approve PRs that meet standards
- Use GitHub's review features (comment, request changes, approve)

**Review checklist:**
- [ ] Code follows project standards
- [ ] Tests are comprehensive and pass
- [ ] Documentation is updated
- [ ] No security vulnerabilities introduced
- [ ] Performance considerations addressed
- [ ] Error handling is appropriate
- [ ] Code is maintainable and readable

## Documentation

### When to Update Documentation

Update documentation when:
- Adding new features
- Changing existing behavior
- Adding configuration options
- Updating dependencies with breaking changes
- Adding new endpoints or APIs

### Documentation Standards

- Keep documentation **up-to-date** with code changes
- Use **clear, concise language**
- Include **code examples** where helpful
- Add **diagrams** for complex concepts
- Update **CLAUDE.md** for development guidelines
- Update **README.md** for user-facing changes

### JavaDoc Guidelines

Add JavaDoc for:
- All **public classes** and interfaces
- All **public methods**
- **Complex private methods**

```java
/**
 * Authenticates a user with the provided credentials.
 *
 * @param email the user's email address
 * @param password the user's password
 * @return an authentication token
 * @throws AuthenticationException if credentials are invalid
 */
public String authenticate(String email, String password) {
    // Implementation
}
```

## Community

### Getting Help

- **GitHub Issues**: For bug reports and feature requests
- **GitHub Discussions**: For questions and general discussion
- **Pull Request Comments**: For specific code questions

### Recognition

Contributors will be recognized in:
- The project's README (Contributors section)
- Release notes (for significant contributions)
- GitHub's contributor graphs

### Stay Updated

- **Watch the repository** for notifications
- **Star the repository** to show support
- **Follow releases** for version updates

## Additional Resources

### Learning Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [GitHub Flow](https://guides.github.com/introduction/flow/)

### Project-Specific Resources

- [README.md](README.md) - Project overview and usage
- [CLAUDE.md](CLAUDE.md) - Detailed development guidelines
- [LICENSE](LICENSE) - Project license

---

## Thank You!

Thank you for contributing to the Java Spring Boot Template! Your efforts help make this project better for everyone.

**Questions?** Don't hesitate to ask in GitHub Issues or Discussions.

**First time contributing?** Welcome! We're here to help.

**Happy coding! ☕**
