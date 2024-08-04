
FROM bellsoft/liberica-openjre-alpine:21.0.1-12

WORKDIR /app

COPY ./target/activity.jar ./activity.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "activity.jar"]