FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ./target/Gateway-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]

