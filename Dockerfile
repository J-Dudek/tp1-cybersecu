FROM maven:3.6.3-openjdk-11 as MAVEN_BUILD

MAINTAINER Julien Dudek

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/tp1-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java","-jar","tp1-0.0.1-SNAPSHOT.jar"]