FROM openjdk:8-jre-alpine
EXPOSE 8010
ADD target/utilizador-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]