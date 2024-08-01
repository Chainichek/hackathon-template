package ru.babim.template.activity.dto.employee;

import java.util.List;
import java.util.UUID;

public record GroupDto(UUID groupId,
                       String title,
                       String description,
                       List<EmployeeDto> employees) {
}
