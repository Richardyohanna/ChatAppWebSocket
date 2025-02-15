FROM maven:3.8.3-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/websocketStudy1-0.0.1-SNAPSHOT.jar websocketStudy1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "websocketStudy1.jar"]