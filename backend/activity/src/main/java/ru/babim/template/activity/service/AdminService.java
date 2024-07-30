package ru.chainichek.hackathon.template.activity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chainichek.hackathon.template.activity.dto.admin.ActivityDto;
import ru.chainichek.hackathon.template.activity.mapper.AdminMapper;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityStatus;
import ru.chainichek.hackathon.template.activity.model.activity.EmployeeStatus;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminMapper adminMapper;

    private final ActivityService activityService;
    private final ActivityEmployeeService activityEmployeeService;

    public ActivityDto findActivity(@NonNull UUID activityId) {
        return adminMapper.map(activityService.findById(activityId));
    }

    public void updateEmployeeStatus(@NonNull UUID activityId,
                                     @NonNull String login,
                                     @NonNull EmployeeStatus status) {
        activityEmployeeService.updateStatus(activityId, login, status);
    }

    public void updateActivityStatus(@NonNull UUID activityId,
                                     @NonNull ActivityStatus activityStatus) {
        activityService.updateStatus(activityService.findById(activityId), activityStatus);
    }
}
