package ru.babim.template.employee.services;


import ru.babim.template.employee.dto.EmployeeInfoDto;

public interface EmployeeService {
    void createEmployee(EmployeeInfoDto employeeInfoDto);
    EmployeeInfoDto getEmployee(String login);
    void removeEmployee(String login);
    void changeEmployee(String login, EmployeeInfoDto newEmployeeInfoDto);
}