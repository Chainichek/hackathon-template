package ru.babim.template.activity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.babim.template.activity.dto.activity.ActivityDto;
import ru.babim.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.babim.template.activity.model.activity.Activity;
import ru.babim.template.activity.model.activity.ActivityEmployee;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {
    default List<String> map(Collection<ActivityEmployee> employees) {
        if (employees == null || employees.isEmpty()) {
            return null;
        }
        return employees.stream()
                .map(x -> x.getId().getEmployeeLogin())
                .toList();
    }

    @Mapping(target = "logins", source = "employees")
    ActivityDto map(Activity activity);

    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Activity map(ActivityRegistrationRequestDto request, String author);

    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    Activity map(@MappingTarget Activity activity, ActivityRegistrationRequestDto request);
}