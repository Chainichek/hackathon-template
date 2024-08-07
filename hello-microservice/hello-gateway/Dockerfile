FROM bellsoft/liberica-openjre-alpine:21.0.1-12

WORKDIR /app

COPY ./target/hello-gateway.jar ./hello-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "hello-gateway.jar"]