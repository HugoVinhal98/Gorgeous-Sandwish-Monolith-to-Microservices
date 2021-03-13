FROM openjdk:8-jre-alpine
EXPOSE 8030
ADD target/sandes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]