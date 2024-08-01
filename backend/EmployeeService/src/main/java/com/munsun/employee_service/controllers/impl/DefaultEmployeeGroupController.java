package com.munsun.employee_service.controllers.impl;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import com.munsun.employee_service.services.EmployeeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DefaultEmployeeGroupController implements EmployeeGroupService {
    private final EmployeeGroupService groupService;

    @Override
    public EmployeeGroupDto getGroupEmployees(UUID groupId) {
        return groupService.getGroupEmployees(groupId);
    }

    @Override
    public EmployeeGroupDto createGroupEmployee(String title, String description) {
        return groupService.createGroupEmployee(title, description);
    }

    @Override
    public EmployeeGroupDto addEmployee(UUID groupId, String login) {
        return groupService.addEmployee(groupId, login);
    }

    @Override
    public void removeGroup(UUID groupId) {
        groupService.removeGroup(groupId);
    }
}
