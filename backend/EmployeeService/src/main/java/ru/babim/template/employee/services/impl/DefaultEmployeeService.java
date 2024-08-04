package ru.babim.template.employee.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.babim.template.employee.dto.EmployeeInfoDto;
import ru.babim.template.employee.dto.request.AccountDto;
import ru.babim.template.employee.exceptions.EmployeeNotFoundException;
import ru.babim.template.employee.mapping.EmployeeMapper;
import ru.babim.template.employee.models.Employee;
import ru.babim.template.employee.queries.payload.EmployeeCreationMessage;
import ru.babim.template.employee.queries.producers.EmployeeProducer;
import ru.babim.template.employee.repositories.EmployeeRepository;
import ru.babim.template.employee.services.EmployeeService;
import ru.babim.template.employee.services.impl.clients.EmployeeFeignClient;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeFeignClient feignClient;
    private final EmployeeProducer producer;

    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void removeEmployee(String login) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
            employee.setIsRemoved(true);
        employeeRepository.save(employee);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public void changeEmployee(String login, EmployeeInfoDto newEmployeeInfo) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
            employee.setName(newEmployeeInfo.name());
            employee.setLastname(newEmployeeInfo.lastname());
            employee.setEmail(newEmployeeInfo.email());
        employeeRepository.save(employee);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER', 'EXPERT')")
    public EmployeeInfoDto getEmployee(String login) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%s not found", login)));
        return employeeMapper.map(employee);
    }
}
