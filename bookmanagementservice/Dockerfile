# syntax=docker/dockerfile:1.7

FROM maven:3.9.9-eclipse-temurin-8 AS build
WORKDIR /workspace

COPY bookmanagementservice/pom.xml bookmanagementservice/pom.xml
COPY bookmanagementservice/library/pom.xml bookmanagementservice/library/pom.xml
COPY bookmanagementservice/application/pom.xml bookmanagementservice/application/pom.xml
COPY bookmanagementservice/library/src bookmanagementservice/library/src
COPY bookmanagementservice/application/src bookmanagementservice/application/src

RUN --mount=type=cache,target=/root/.m2 \
    mvn -f bookmanagementservice/pom.xml -pl application -am clean package -DskipTests

FROM eclipse-temurin:8-jre
WORKDIR /app

COPY --from=build /workspace/bookmanagementservice/application/target/application-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
