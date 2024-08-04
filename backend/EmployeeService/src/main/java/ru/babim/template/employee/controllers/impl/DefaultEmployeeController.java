package ru.babim.template.employee.controllers.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.babim.template.employee.dto.EmployeeInfoDto;
import ru.babim.template.employee.services.EmployeeService;

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
