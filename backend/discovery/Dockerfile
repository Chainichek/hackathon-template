
FROM bellsoft/liberica-openjre-alpine:17.0.9

WORKDIR /app

COPY ./target/discovery.jar ./discovery.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "discovery.jar"]