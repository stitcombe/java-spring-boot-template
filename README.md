# Java Spring Boot Template
## Purpose
This template is preconfigured for a backend REST API gateway purpose.
## Configuring
* `gradle.properties`
  * `maven_group` is the groupId so set it to the group id of your project,
    for example `com.replit`  
## Dependencies
* The following dependencies are pre-configured in `build.gradle`:
  * `spring-boot-starter-actuator`
  * `spring-boot-starter-web`
  * `spring-boot-starter-log4j2`
  * `spring-boot-starter-data-jpa`
  * `spring-cloud-starter-sleuth`
  * `springdoc-openapi-ui`
  * `modelmapper`
## Testing
* `spring-boot-starter-test`
* `JUnit`
* `jacoco`
## Run
To start the Spring Boot application, use command `./gradlew bootRun` or if you're using replit click the Run button.

Other commands:
* `./gradlew test` runs tests
* `./gradlew clean` clears the build directory
* `./gradlew build` new build from code