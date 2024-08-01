package com.munsun.employee_service.mapping;

import com.munsun.employee_service.dto.request.AccountDto;
import com.munsun.employee_service.dto.EmployeeInfoDto;
import com.munsun.employee_service.models.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee map(EmployeeInfoDto employeeInfoDto);
    EmployeeInfoDto map(Employee employee);
    AccountDto mapToAccount(EmployeeInfoDto employeeInfoDto);
}
