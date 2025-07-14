# Stage 1: Build the application using Maven and JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY order-service/pom.xml ./order-service/
RUN mvn -f order-service/pom.xml dependency:go-offline

# Copy source code
COPY order-service ./order-service

# Package the Spring Boot application (skip tests)
RUN mvn -f order-service/pom.xml clean package -DskipTests

# Stage 2: Use a smaller JRE base image
FROM eclipse-temurin:17-jre-alpine

# Create working directory inside the container
WORKDIR /app

# Copy the generated JAR file from the builder stage
COPY --from=build /app/order-service/target/*.jar app.jar

# Expose the application port (change if different)
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
