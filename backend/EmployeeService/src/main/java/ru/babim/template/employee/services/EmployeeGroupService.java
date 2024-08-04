package ru.babim.template.employee.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.babim.template.employee.dto.response.EmployeeGroupDto;

import java.util.UUID;

public interface EmployeeGroupService {

    EmployeeGroupDto getGroupEmployees(UUID groupId);

    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    EmployeeGroupDto createGroupEmployee(String title, String description);

    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER', 'EXPERT')")
    EmployeeGroupDto addEmployee(UUID groupId, String login);

    @PreAuthorize("hasAnyRole('ADMIN')")
    void removeGroup(UUID groupId);
}
