package ru.chainichek.hackathon.template.activity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chainichek.hackathon.template.activity.model.EmployeeStatus;

import java.util.UUID;

@RequestMapping("${app.mvc.context-path}/admin")
public interface AdminApi {
    @PatchMapping("/activity/{activityId}/employee/{login}")
    ResponseEntity<?> updateEmployeeStatus(@PathVariable UUID activityId,
                                           @PathVariable String login,
                                           @RequestParam EmployeeStatus employeeStatus);
}
