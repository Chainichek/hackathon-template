package com.munsun.employee_service.controllers.impl;

import com.munsun.employee_service.dto.EmployeeInfoDto;
import com.munsun.employee_service.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/employees")
public class DefaultEmployeeController implements EmployeeService {
    private final EmployeeService service;

    @DeleteMapping
    @Override
    public void removeEmployee(String login) {
        service.removeEmployee(login);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    @PostMapping
    @Override
    public void createEmployee(EmployeeInfoDto employeeInfoDto) {
        service.createEmployee(employeeInfoDto);
    }

    @PatchMapping
    @Override
    public void changeEmployee(String login, EmployeeInfoDto newEmployeeInfoDto) {
        service.changeEmployee(login, newEmployeeInfoDto);
    }

    @GetMapping
    @Override
    public EmployeeInfoDto getEmployee(String login) {
        return service.getEmployee(login);
    }
}
