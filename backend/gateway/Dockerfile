FROM bellsoft/liberica-openjre-alpine:17.0.9

WORKDIR /app

COPY --from=build /target/gateway.jar ./gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gateway.jar"]