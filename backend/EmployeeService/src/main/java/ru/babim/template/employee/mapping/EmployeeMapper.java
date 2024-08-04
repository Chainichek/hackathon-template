package ru.babim.template.employee.mapping;

import org.mapstruct.Mapper;
import ru.babim.template.employee.dto.EmployeeInfoDto;
import ru.babim.template.employee.dto.request.AccountDto;
import ru.babim.template.employee.models.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee map(EmployeeInfoDto employeeInfoDto);
    EmployeeInfoDto map(Employee employee);
    AccountDto mapToAccount(EmployeeInfoDto employeeInfoDto);
}
