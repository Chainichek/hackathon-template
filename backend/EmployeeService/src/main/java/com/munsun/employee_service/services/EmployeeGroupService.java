package com.munsun.employee_service.services;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface EmployeeGroupService {
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER', 'EXPERT')")
    EmployeeGroupDto getGroupEmployees(UUID groupId);


    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    EmployeeGroupDto createGroupEmployee(String title, String description);

    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER', 'EXPERT')")
    EmployeeGroupDto addEmployee(UUID groupId, String login);

    @PreAuthorize("hasAnyRole('ADMIN')")
    void removeGroup(UUID groupId);
}
