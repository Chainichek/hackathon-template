package ru.babim.template.employee.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.babim.template.employee.dto.response.EmployeeGroupDto;
import ru.babim.template.employee.exceptions.EmployeeGroupNotFoundException;
import ru.babim.template.employee.exceptions.EmployeeNotFoundException;
import ru.babim.template.employee.mapping.EmployeeGroupMapper;
import ru.babim.template.employee.models.Employee;
import ru.babim.template.employee.models.EmployeeGroup;
import ru.babim.template.employee.repositories.EmployeeGroupRepository;
import ru.babim.template.employee.repositories.EmployeeRepository;
import ru.babim.template.employee.services.EmployeeGroupService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeGroupService implements EmployeeGroupService {
    private final EmployeeGroupRepository employeeGroupRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeGroupMapper employeeGroupMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER', 'EXPERT')")
    @Override
    public EmployeeGroupDto getGroupEmployees(UUID groupId) {
        EmployeeGroup group = employeeGroupRepository.findById(groupId)
                .orElseThrow(() -> new EmployeeGroupNotFoundException(String.format("Group with id=%s not exists", groupId)));
        return employeeGroupMapper.map(group);
    }

    @Override
    public EmployeeGroupDto createGroupEmployee(String title, String description) {
        EmployeeGroup emptyGroup = EmployeeGroup.builder()
                .title(title)
                .description(description)
                .build();
        employeeGroupRepository.save(emptyGroup);
        return employeeGroupMapper.map(emptyGroup);
    }

    @Override
    public void removeGroup(UUID groupId) {
        EmployeeGroup group = employeeGroupRepository.findById(groupId)
                .orElseThrow(() -> new EmployeeGroupNotFoundException(String.format("Group with id=%s not exists", groupId)));
            group.setIsRemoved(true);
        employeeGroupRepository.save(group);
    }

    @Override
    public EmployeeGroupDto addEmployee(UUID groupId, String login) {
        EmployeeGroup group = employeeGroupRepository.findById(groupId)
                .orElseThrow(() -> new EmployeeGroupNotFoundException(String.format("Group with id=%s not exists", groupId)));
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
        group.getEmployees().add(employee);
        employeeGroupRepository.save(group);
        return employeeGroupMapper.map(group);
    }
}
