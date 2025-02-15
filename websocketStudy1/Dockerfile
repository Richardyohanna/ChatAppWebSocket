# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline  # Pre-download dependencies to speed up builds

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/websocketStudy1-0.0.1-SNAPSHOT.jar websocketStudy1.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "websocketStudy1.jar"]
