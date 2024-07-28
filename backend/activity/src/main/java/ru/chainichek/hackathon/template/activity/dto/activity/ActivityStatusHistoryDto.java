package ru.chainichek.hackathon.template.activity.dto.activity;

import ru.chainichek.hackathon.template.activity.model.activity.ActivityStatus;

import java.time.LocalDateTime;

public record ActivityStatusHistoryDto(ActivityStatus status,
                                       LocalDateTime time) {
    public ActivityStatusHistoryDto(ActivityStatus status)  {
        this(status, LocalDateTime.now());
    }
}