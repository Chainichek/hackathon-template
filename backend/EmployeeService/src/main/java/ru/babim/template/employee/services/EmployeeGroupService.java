package ru.babim.template.employee.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.babim.template.employee.dto.response.EmployeeGroupDto;

import java.util.UUID;

public interface EmployeeGroupService {
    EmployeeGroupDto getGroupEmployees(UUID groupId);
    EmployeeGroupDto createGroupEmployee(String title, String description);
    EmployeeGroupDto addEmployee(UUID groupId, String login);
    void removeGroup(UUID groupId);
}
