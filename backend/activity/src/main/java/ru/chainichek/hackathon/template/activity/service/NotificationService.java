package ru.chainichek.hackathon.template.activity.service;

import org.springframework.stereotype.Service;
import ru.chainichek.hackathon.template.activity.dto.employee.EmployeeDto;

import java.util.UUID;

@Service
public class NotificationService {
    public void notify(EmployeeDto employee, UUID activityId) {}
}
