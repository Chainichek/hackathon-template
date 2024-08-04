package ru.babim.template.employee.dto.response;

import ru.babim.template.employee.dto.EmployeeInfoDto;

import java.util.List;
import java.util.UUID;

public record EmployeeGroupDto(
        UUID groupId,
        String title,
        String description,
        List<EmployeeInfoDto> employees
) {}