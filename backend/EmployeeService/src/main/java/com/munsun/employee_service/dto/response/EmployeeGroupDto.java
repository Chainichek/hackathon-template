package com.munsun.employee_service.dto.response;

import com.munsun.employee_service.dto.EmployeeInfoDto;

import java.util.List;
import java.util.UUID;

public record EmployeeGroupDto(
        UUID groupId,
        String title,
        String description,
        List<EmployeeInfoDto> employees
) {}