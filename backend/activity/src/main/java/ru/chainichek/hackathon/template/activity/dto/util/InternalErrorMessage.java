package ru.chainichek.hackathon.template.activity.dto.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import static ru.chainichek.hackathon.template.activity.validation.value.ValidationValue.DATE_TIME_FORMAT_PATTERN;

public record InternalErrorMessage (@JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
                                    LocalDateTime timestamp,
                                    String title,
                                    int status,
                                    String detail,
                                    String[] stackTrace,
                                    String instance) {
}
