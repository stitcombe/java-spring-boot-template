# Java Spring Boot Template
## Configuring
* `gradle.properties`
  * `maven_group` is the groupId so set it to the group id of your project,
    for example `com.replit`  

* `build.gradle`
  * To set the entrypoint class edit the following lines
      ```java
      ext {
         javaMainClass = "${project.maven_group}.${archivesBaseName}.CLASS_HERE"
      }
      ```
