package com.munsun.employee_service.controllers.impl;

import com.munsun.employee_service.dto.EmployeeInfoDto;
import com.munsun.employee_service.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DefaultEmployeeController implements EmployeeService {
    private final EmployeeService service;

    @Override
    public void removeEmployee(String login) {
        service.removeEmployee(login);
    }

    @Override
    public void createEmployee(EmployeeInfoDto employeeInfoDto) {
        service.createEmployee(employeeInfoDto);
    }

    @Override
    public void changeEmployee(String login, EmployeeInfoDto newEmployeeInfoDto) {
        service.changeEmployee(login, newEmployeeInfoDto);
    }

    @Override
    public EmployeeInfoDto getEmployee(String login) {
        return service.getEmployee(login);
    }
}
