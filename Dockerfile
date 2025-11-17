# Multi-stage build: Build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Production stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (using the same port as in your application.properties)
EXPOSE 8090

# Run the application

ENTRYPOINT ["java", "-jar", "app.jar"]docker build -t chand241/springboot-app .

