FROM bellsoft/liberica-openjre-alpine:21.0.1-12

WORKDIR /app

COPY ./target/hello-microservice.jar ./hello-microservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "hello-microservice.jar"]