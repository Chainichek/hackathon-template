package com.munsun.employee_service.services;

import com.munsun.employee_service.dto.EmployeeInfoDto;

public interface EmployeeService {
    void createEmployee(EmployeeInfoDto employeeInfoDto);
    EmployeeInfoDto getEmployee(String login);
    void removeEmployee(String login);
    void changeEmployee(String login, EmployeeInfoDto newEmployeeInfoDto);
}
