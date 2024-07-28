package com.munsun.employee_service.mapping;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import com.munsun.employee_service.models.EmployeeGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeGroupMapper {
    EmployeeGroupDto map(EmployeeGroup emptyGroup);
}
