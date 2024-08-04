package ru.babim.template.employee.mapping;

import org.mapstruct.Mapper;
import ru.babim.template.employee.dto.response.EmployeeGroupDto;
import ru.babim.template.employee.models.EmployeeGroup;

@Mapper(componentModel = "spring")
public interface EmployeeGroupMapper {
    EmployeeGroupDto map(EmployeeGroup emptyGroup);
}
