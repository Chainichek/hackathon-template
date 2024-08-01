package ru.babim.template.activity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.babim.template.activity.dto.admin.ActivityDto;
import ru.babim.template.activity.mapper.AdminMapper;
import ru.babim.template.activity.model.activity.ActivityStatus;
import ru.babim.template.activity.model.activity.EmployeeStatus;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@PreAuthorize("hasRole('ADMIN')")
public class AdminService {
    private final AdminMapper adminMapper;

    private final ActivityService activityService;
    private final ActivityEmployeeService activityEmployeeService;

    public ActivityDto findActivity(@NonNull UUID activityId) {
        return adminMapper.map(activityService.findById(activityId));
    }

    @Transactional
    public void updateEmployeeStatus(@NonNull UUID activityId,
                                     @NonNull String login,
                                     @NonNull EmployeeStatus status) {
        activityEmployeeService.updateStatus(activityId, login, status);
    }

    @Transactional
    public void updateActivityStatus(@NonNull UUID activityId,
                                     @NonNull ActivityStatus activityStatus) {
        activityService.updateStatus(activityService.findById(activityId), activityStatus);
    }
}
