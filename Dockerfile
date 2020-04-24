### BUILD image
FROM maven:3-jdk-8 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
COPY oocl-maven-settings.xml /build
#Copy source code
COPY src /build/src
# Build application
RUN mvn package -Dspring.cloud.contract.verifier.skip=true -s oocl-maven-settings.xml

#Run the application
FROM openjdk:8-jdk-alpine
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--server.port=9300"]