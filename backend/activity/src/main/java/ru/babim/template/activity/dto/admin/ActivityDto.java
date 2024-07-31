package ru.babim.template.activity.dto.admin;

import ru.babim.template.activity.dto.activity.ActivityStatusHistoryDto;
import ru.babim.template.activity.model.activity.ActivityStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ActivityDto(
        UUID id,
        String title,
        String description,
        String author,
        Set<ActivityEmployeeDto> employees,
        ActivityStatus status,
        List<ActivityStatusHistoryDto> statusHistory,
        LocalDateTime startAt,
        LocalDateTime endAt,
        LocalDateTime createdAt
) {}