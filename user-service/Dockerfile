# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy everything into the container
COPY . .

# Download dependencies and build the app
RUN mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy only the final jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (adjust to your app's port if needed)
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]