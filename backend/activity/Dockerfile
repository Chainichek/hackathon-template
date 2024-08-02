
FROM bellsoft/liberica-openjre-alpine:17.0.9

WORKDIR /app

COPY --from=build /target/activity.jar ./activity.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "activity.jar"]