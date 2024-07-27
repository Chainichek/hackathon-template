package ru.chainichek.hackathon.template.activity.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityStatus;

import java.time.LocalDateTime;

import static ru.chainichek.hackathon.template.activity.validation.value.ValidationValue.DATE_TIME_FORMAT_PATTERN;

public record ActivityStatusHistoryDto(ActivityStatus status,
                                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT_PATTERN)
                                       LocalDateTime time) {
    public ActivityStatusHistoryDto(ActivityStatus status)  {
        this(status, LocalDateTime.now());
    }
}