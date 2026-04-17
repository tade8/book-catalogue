# syntax=docker/dockerfile:1.7

FROM maven:3.9.9-eclipse-temurin-8 AS build
WORKDIR /workspace

COPY pom.xml pom.xml
COPY bookmanagementservice/pom.xml bookmanagementservice/pom.xml
COPY bookmanagementservice/library/pom.xml bookmanagementservice/library/pom.xml
COPY bookmanagementservice/application/pom.xml bookmanagementservice/application/pom.xml
COPY webservice/pom.xml webservice/pom.xml

COPY bookmanagementservice/library/src bookmanagementservice/library/src
COPY webservice/src webservice/src

RUN --mount=type=cache,target=/root/.m2 \
    mvn -f pom.xml -pl webservice -am clean package -DskipTests

FROM eclipse-temurin:8-jre
WORKDIR /app

COPY --from=build /workspace/webservice/target/webservice-0.0.1.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
