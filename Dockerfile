### BUILD image
FROM maven:3-jdk-8 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package -Dspring.cloud.contract.verifier.skip=true

#Run the application
FROM openjdk:8-jdk-alpine
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--server.port=9300"]