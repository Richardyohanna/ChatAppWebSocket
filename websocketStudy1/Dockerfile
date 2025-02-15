FROM maven:3.9.6-eclipse-temurin-22-jammy as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
COPY --from=build /target/websocketStudy1-0.0.1-SNAPSHOT.jar.original websocketStudy1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "websocketStudy1.jar"]