package ru.chainichek.hackathon.template.activity.dto.activity;

import java.time.LocalDateTime;
import java.util.Set;

public record ActivityDto(String title,
                          String description,
                          Set<String> logins,
                          LocalDateTime startAt,
                          LocalDateTime endAt) {
}
