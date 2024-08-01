package com.munsun.employee_service.services;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;

import java.util.UUID;

public interface EmployeeGroupService {
    EmployeeGroupDto getGroupEmployees(UUID groupId);

    EmployeeGroupDto createGroupEmployee(String title, String description);

    EmployeeGroupDto addEmployee(UUID groupId, String login);

    void removeGroup(UUID groupId);
}
