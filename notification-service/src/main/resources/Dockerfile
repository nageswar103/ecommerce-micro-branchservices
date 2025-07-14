# Stage 1: Build the Spring Boot application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven config and download dependencies
COPY inventory-service/pom.xml ./inventory-service/
RUN mvn -f inventory-service/pom.xml dependency:go-offline

# Copy the entire source
COPY inventory-service ./inventory-service

# Build the application (skip tests to speed up)
RUN mvn -f inventory-service/pom.xml clean package -DskipTests

# Stage 2: Run the app in a smaller JRE image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/inventory-service/target/*.jar app.jar

# Optional: expose app port (edit if needed)
EXPOSE 8087

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
