package ru.babim.template.activity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.babim.template.activity.api.AdminApi;
import ru.babim.template.activity.model.activity.ActivityStatus;
import ru.babim.template.activity.model.activity.EmployeeStatus;
import ru.babim.template.activity.service.AdminService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AdminController implements AdminApi {
    private final AdminService adminService;
    @Override
    public ResponseEntity<?> findActivity(UUID activityId) {
        return ResponseEntity.ok(adminService.findActivity(activityId));
    }

    @Override
    public ResponseEntity<?> updateEmployeeStatus(UUID activityId, String login, EmployeeStatus status) {
        adminService.updateEmployeeStatus(activityId, login, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> updateActivityStatus(UUID activityId, ActivityStatus status) {
        adminService.updateActivityStatus(activityId, status);
        return ResponseEntity.noContent().build();
    }
}
