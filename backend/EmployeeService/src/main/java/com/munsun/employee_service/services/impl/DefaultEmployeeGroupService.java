package com.munsun.employee_service.services.impl;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import com.munsun.employee_service.exceptions.EmployeeGroupNotFoundException;
import com.munsun.employee_service.exceptions.EmployeeNotFoundException;
import com.munsun.employee_service.mapping.EmployeeGroupMapper;
import com.munsun.employee_service.models.Employee;
import com.munsun.employee_service.models.EmployeeGroup;
import com.munsun.employee_service.repositories.EmployeeGroupRepository;
import com.munsun.employee_service.repositories.EmployeeRepository;
import com.munsun.employee_service.services.EmployeeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeGroupService implements EmployeeGroupService {
    private final EmployeeGroupRepository employeeGroupRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeGroupMapper employeeGroupMapper;

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
