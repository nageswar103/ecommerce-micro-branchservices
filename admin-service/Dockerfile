# Start with a base image that has JDK 17
FROM eclipse-temurin:17-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# 2nd stage: minimal runtime image
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (optional, default is 8080)
EXPOSE 8089

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
