package ru.chainichek.hackathon.template.activity.dto.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.chainichek.hackathon.template.activity.validation.value.ValidationValue;

import java.time.LocalDateTime;

public record ErrorMessage(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ValidationValue.DATE_TIME_FORMAT_PATTERN)
                           LocalDateTime timestamp,
                           String title,
                           int status,
                           String detail,
                           String instance) {
}
