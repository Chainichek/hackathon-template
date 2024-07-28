package ru.chainichek.hackathon.template.activity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.chainichek.hackathon.template.activity.dto.employee.EmployeeDto;
import ru.chainichek.hackathon.template.activity.model.activity.Activity;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityEmployee;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityEmployeeMapper {

    @Mapping(target = "activity", source = "activity")
    @Mapping(target = "id", expression = "java(new ActivityEmployee.ActivityEmployeeKey(activity.getId(), employee.login()))")
    @Mapping(target = "status", ignore = true)
    ActivityEmployee map(EmployeeDto employee, Activity activity);
}
