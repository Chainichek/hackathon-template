package ru.babim.template.notification.dto.notification;

import ru.babim.template.notification.model.NotificationType;

import java.util.UUID;

public record NotificationMessage(String address,
                                  String login,
                                  String name,
                                  String lastname,
                                  UUID resourceId,
                                  NotificationType notificationType) {
}
