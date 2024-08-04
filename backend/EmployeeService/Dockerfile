FROM bellsoft/liberica-openjre-alpine:17.0.9

WORKDIR /app

COPY ./target/employee-service.jar ./employee-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "employee-service.jar"]