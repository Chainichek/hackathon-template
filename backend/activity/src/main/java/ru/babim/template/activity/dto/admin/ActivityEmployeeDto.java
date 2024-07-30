package ru.chainichek.hackathon.template.activity.dto.admin;

import ru.chainichek.hackathon.template.activity.model.activity.EmployeeStatus;

public record ActivityEmployeeDto(String login,
                                  EmployeeStatus status) {
}
