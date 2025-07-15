# Stage 1: Build the Spring Boot application using Maven and JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY user-service/pom.xml ./user-service/
RUN mvn -f user-service/pom.xml dependency:go-offline

# Copy the rest of the source code
COPY user-service ./user-service

# Package the application (skip tests for faster builds)
RUN mvn -f user-service/pom.xml clean package -DskipTests

# Stage 2: Use a lightweight JRE to run the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/user-service/target/*.jar app.jar

# Expose the port (adjust if your service runs on a different port)
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
