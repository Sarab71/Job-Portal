# Use official OpenJDK 21 base image
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
