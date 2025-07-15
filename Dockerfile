# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only the pom.xml first and download dependencies
COPY discount-service/pom.xml ./discount-service/
RUN mvn -f discount-service/pom.xml dependency:go-offline

# Copy the complete source code
COPY discount-service ./discount-service

# Build the application (skip tests)
RUN mvn -f discount-service/pom.xml clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:17-jre-alpine

# Set working directory in the container
WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/discount-service/target/*.jar app.jar

# Expose the port your application runs on (change if needed)
EXPOSE 8090

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
