package ru.babim.template.activity.dto.activity;

import ru.babim.template.activity.model.activity.ActivityStatus;

import java.time.LocalDateTime;

public record ActivityStatusHistoryDto(ActivityStatus status,
                                       LocalDateTime time) {
    public ActivityStatusHistoryDto(ActivityStatus status)  {
        this(status, LocalDateTime.now());
    }
}