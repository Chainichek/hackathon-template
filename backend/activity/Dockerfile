FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package

FROM bellsoft/liberica-openjre-alpine:17.0.9

WORKDIR /app

COPY --from=build /app/target/activity.jar ./activity.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "activity.jar"]