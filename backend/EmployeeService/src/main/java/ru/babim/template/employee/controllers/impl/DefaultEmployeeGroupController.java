package ru.babim.template.employee.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.babim.template.employee.dto.response.EmployeeGroupDto;
import ru.babim.template.employee.services.EmployeeGroupService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/employees")
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
