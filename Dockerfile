# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY cart-service/pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY cart-service ./cart-service

# Build the application (skip tests for faster build)
RUN mvn -f cart-service/pom.xml clean package -DskipTests

# Stage 2: Run the application using a minimal JDK image
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/cart-service/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8084

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
