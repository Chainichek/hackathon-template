FROM bellsoft/liberica-openjre-alpine:17.0.9
EXPOSE 8080
WORKDIR /opt/app
COPY ./target/EmployeeService-0.0.1-SNAPSHOT.jar ./service.jar
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=admin
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:9999/test_db1
ENV KAFKA_BOOTSTRAPSERVERS=localhost:9092
ENV KAFKA_CLIENT_AUTH_SERVICE_URL=http://localhost:8081
ENV KAFKA_TOPICS_CREATE_EMPLOYEE=create-employee
CMD ["java", "-jar", "/opt/app/service.jar"]