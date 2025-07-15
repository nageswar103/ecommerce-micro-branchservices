# Stage 1: Build the Spring Boot application
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy only pom.xml first to leverage Docker cache for dependencies
COPY review-service/pom.xml ./review-service/
RUN mvn -f review-service/pom.xml dependency:go-offline

# Copy the full source code
COPY review-service ./review-service

# Package the application (skip tests for faster builds)
RUN mvn -f review-service/pom.xml clean package -DskipTests

# Stage 2: Run the application in a minimal JRE image
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/review-service/target/*.jar app.jar

# Expose the application port (default is 8080; adjust if needed)
EXPOSE 8088

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
