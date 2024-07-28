package ru.chainichek.hackathon.template.activity.dto.activity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ActivityDto(UUID id,
                          String title,
                          String description,
                          List<String> logins,
                          LocalDateTime startAt,
                          LocalDateTime endAt) {
}
