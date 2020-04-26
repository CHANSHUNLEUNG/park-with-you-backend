#Run the application
FROM openjdk:8-jdk-alpine
COPY ./target/parking-lot-application.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--server.port=9300"]