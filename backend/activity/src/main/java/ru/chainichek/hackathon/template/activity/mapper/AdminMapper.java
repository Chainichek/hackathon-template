package ru.chainichek.hackathon.template.activity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.chainichek.hackathon.template.activity.dto.admin.ActivityDto;
import ru.chainichek.hackathon.template.activity.dto.admin.ActivityEmployeeDto;
import ru.chainichek.hackathon.template.activity.model.activity.Activity;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityEmployee;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminMapper {
    @Mapping(target = "login", source = "id.employeeLogin")
    ActivityEmployeeDto map(ActivityEmployee employee);

    ActivityDto map(Activity activity);
}
