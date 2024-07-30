package ru.babim.template.activity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.babim.template.activity.dto.employee.EmployeeDto;
import ru.babim.template.activity.dto.notification.NotificationMessage;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
    @Mapping(target = "lastname", source = "employee.lastname")
    @Mapping(target = "address", source = "employee.email")
    NotificationMessage map(EmployeeDto employee, UUID activityId);
}
