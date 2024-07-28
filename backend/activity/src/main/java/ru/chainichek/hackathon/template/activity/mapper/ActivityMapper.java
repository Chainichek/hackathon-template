package ru.chainichek.hackathon.template.activity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityDto;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.chainichek.hackathon.template.activity.model.activity.Activity;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityEmployee;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {
    @Named("mapToLogins")
    default List<String> mapToLogins(Collection<ActivityEmployee> employees) {
        return employees.stream()
                .map(x -> x.getId().getEmployeeLogin())
                .toList();
    }

    @Mapping(target = "logins", source = "employees", qualifiedByName = "mapToLogins")
    ActivityDto mapToActivityDto(Activity activity);

    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Activity mapToActivity(ActivityRegistrationRequestDto request, String author);

    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    Activity updateActivity(@MappingTarget Activity activity, ActivityRegistrationRequestDto request);
}