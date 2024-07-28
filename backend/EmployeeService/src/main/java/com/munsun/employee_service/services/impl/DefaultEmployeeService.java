package com.munsun.employee_service.services.impl;

import com.munsun.employee_service.dto.request.AccountDto;
import com.munsun.employee_service.dto.EmployeeInfoDto;
import com.munsun.employee_service.exceptions.EmployeeNotFoundException;
import com.munsun.employee_service.mapping.EmployeeMapper;
import com.munsun.employee_service.models.Employee;
import com.munsun.employee_service.queries.payload.EmployeeCreationMessage;
import com.munsun.employee_service.queries.producers.EmployeeProducer;
import com.munsun.employee_service.repositories.EmployeeRepository;
import com.munsun.employee_service.services.EmployeeService;
import com.munsun.employee_service.services.impl.clients.EmployeeFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeFeignClient feignClient;
    private final EmployeeProducer producer;

    @Override
    public void createEmployee(EmployeeInfoDto employeeInfoDto) {
        AccountDto accountDto = employeeMapper.mapToAccount(employeeInfoDto);
        feignClient.getAccount(accountDto);
        Employee employee = employeeMapper.map(employeeInfoDto);
        employeeRepository.save(employee);
        producer.sendSuccessCreateEmployee(new EmployeeCreationMessage(
                employeeInfoDto.email(), employeeInfoDto.name(), employeeInfoDto.lastname()));
    }

    @Override
    public void removeEmployee(String login) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
            employee.setIsRemoved(true);
        employeeRepository.save(employee);
    }

    @Override
    public void changeEmployee(String login, EmployeeInfoDto newEmployeeInfo) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
            employee.setName(newEmployeeInfo.name());
            employee.setLastname(newEmployeeInfo.lastname());
            employee.setEmail(newEmployeeInfo.email());
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeInfoDto getEmployee(String login) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
        return employeeMapper.map(employee);
    }
}
