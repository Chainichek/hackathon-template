package com.munsun.employee_service.queries.producers.impl;

import com.munsun.employee_service.queries.payload.EmployeeCreationMessage;
import com.munsun.employee_service.queries.producers.EmployeeProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

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
