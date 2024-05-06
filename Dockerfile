FROM openjdk:8-jdk-alpine
EXPOSE 8082
COPY target/telemetry-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "telemetry-0.0.1-SNAPSHOT.jar"]
