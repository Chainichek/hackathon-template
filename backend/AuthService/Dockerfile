FROM bellsoft/liberica-openjre-alpine:17.0.9

WORKDIR /app

COPY ./target/auth-service.jar ./auth-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "activity.jar"]