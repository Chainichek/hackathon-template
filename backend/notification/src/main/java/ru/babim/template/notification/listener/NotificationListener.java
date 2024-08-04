package ru.babim.template.notification.listener;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.babim.lib.logger.annotation.ConsumerLoggable;
import ru.babim.template.notification.dto.notification.NotificationMessage;

@RequiredArgsConstructor
@Service
@Validated
@ConsumerLoggable
public class NotificationListener {
    @KafkaListener(topics = "${app.kafka.topic.create-employee}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenCreateEmployee(@Valid @NotNull NotificationMessage message) {}

    @KafkaListener(topics = "${app.kafka.topic.activity-notification}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenActivityNotification(@Valid @NotNull NotificationMessage message) {}
}
