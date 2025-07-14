# Use Maven to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Package the app (build the jar)
RUN mvn clean package -DskipTests

# --------------------------------------------------------------------

# Use a smaller image for running the app
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy only the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

