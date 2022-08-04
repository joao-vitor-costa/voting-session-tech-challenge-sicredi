FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src src

RUN mvn package --batch-mode

FROM eclipse-temurin:17-jre-alpine AS release

COPY --from=build /build/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]