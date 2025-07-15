# Stage 1: Build the Spring Boot app with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only pom.xml first and download dependencies (faster builds)
COPY product-service/pom.xml ./product-service/
RUN mvn -f product-service/pom.xml dependency:go-offline

# Copy the entire project
COPY product-service ./product-service

# Build the JAR file (skip tests for speed)
RUN mvn -f product-service/pom.xml clean package -DskipTests

# Stage 2: Create a lightweight JRE runtime image
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy JAR file from builder stage
COPY --from=build /app/product-service/target/*.jar app.jar

# Expose application port (adjust if different)
EXPOSE 8082

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
