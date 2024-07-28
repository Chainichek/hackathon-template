package ru.chainichek.hackathon.template.activity.dto.activity;

import java.time.LocalDateTime;
import java.util.List;

public record ActivityDto(String title,
                          String description,
                          List<String> logins,
                          LocalDateTime startAt,
                          LocalDateTime endAt) {
}
