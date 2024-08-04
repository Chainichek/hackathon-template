package ru.babim.template.activity.dto.notification;

import java.util.UUID;

public record NotificationMessage(String address,
                                  String login,
                                  String name,
                                  String lastname,
                                  UUID resourceId) {
}
