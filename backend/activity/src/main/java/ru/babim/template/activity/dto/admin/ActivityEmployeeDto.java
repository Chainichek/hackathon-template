package ru.babim.template.activity.dto.admin;

import ru.babim.template.activity.model.activity.EmployeeStatus;

public record ActivityEmployeeDto(String login,
                                  EmployeeStatus status) {
}
