package ru.babim.template.activity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.babim.template.activity.dto.employee.EmployeeDto;
import ru.babim.template.activity.dto.notification.NotificationMessage;
import ru.babim.template.activity.mapper.NotificationMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationMapper notificationMapper;
    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;
    @Value("${app.kafka.topic.activity-notification}")
    private String activityNotificationTopic;

    public void notify(EmployeeDto employee, UUID activityId) {
        kafkaTemplate.send(activityNotificationTopic, notificationMapper.map(employee, activityId));
    }
}
