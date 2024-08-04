package ru.babim.template.employee.queries.producers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.babim.template.employee.queries.payload.EmployeeCreationMessage;
import ru.babim.template.employee.queries.producers.EmployeeProducer;

@Component
@RequiredArgsConstructor
public class KafkaEmployeeProducer implements EmployeeProducer {
    private final KafkaTemplate<String, EmployeeCreationMessage> kafkaTemplate;
    @Value("${kafka.topics.create_employee}")
    private String createEmployeeTopic;

    @Override
    public void sendSuccessCreateEmployee(EmployeeCreationMessage employeeCreationMessage) {
        kafkaTemplate.send(createEmployeeTopic, employeeCreationMessage);
    }
}
