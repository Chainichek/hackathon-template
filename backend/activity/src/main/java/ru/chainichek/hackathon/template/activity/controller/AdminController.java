package ru.chainichek.hackathon.template.activity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chainichek.hackathon.template.activity.api.AdminApi;
import ru.chainichek.hackathon.template.activity.model.EmployeeStatus;

import java.util.UUID;

@RestController
public class AdminController implements AdminApi {
    @Override
    public ResponseEntity<?> updateEmployeeStatus(UUID activityId, String login, EmployeeStatus employeeStatus) {
        return null;
    }
}
