FROM openjdk:21-jdk
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar
# Expose the port that the Spring Boot app will run on
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/demo.jar"]