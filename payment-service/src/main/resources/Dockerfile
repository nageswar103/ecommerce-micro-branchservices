# Stage 1: Build the Spring Boot app with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY payment-service/pom.xml ./payment-service/
RUN mvn -f payment-service/pom.xml dependency:go-offline

# Copy source code
COPY payment-service ./payment-service

# Package the application (skip tests for faster build)
RUN mvn -f payment-service/pom.xml clean package -DskipTests

# Stage 2: Run the app using a lightweight JRE image
FROM eclipse-temurin:17-jre-alpine

# Working directory inside the container
WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/payment-service/target/*.jar app.jar

# Expose the port (change if different in application.properties)
EXPOSE 8085

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
