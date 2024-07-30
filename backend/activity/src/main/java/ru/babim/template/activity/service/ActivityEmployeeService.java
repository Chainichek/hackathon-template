package ru.babim.template.activity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.babim.template.activity.dto.employee.EmployeeDto;
import ru.babim.template.activity.exception.NotFoundException;
import ru.babim.template.activity.mapper.ActivityEmployeeMapper;
import ru.babim.template.activity.model.activity.Activity;
import ru.babim.template.activity.model.activity.ActivityEmployee;
import ru.babim.template.activity.model.activity.EmployeeStatus;
import ru.babim.template.activity.repo.ActivityEmployeeRepo;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ActivityEmployeeService {
    private final ActivityEmployeeRepo activityEmployeeRepo;
    private final ActivityEmployeeMapper activityEmployeeMapper;

    public ActivityEmployee findByActivityIdAndEmployeeLogin(@NonNull UUID activityId, @NonNull String login) {
        final ActivityEmployee.ActivityEmployeeKey id = new ActivityEmployee.ActivityEmployeeKey(activityId, login);
        return activityEmployeeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ACTIVITY_EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE, id.toString()));
    }

    public void create(@NonNull Activity activity, @NonNull EmployeeDto employee) {
        activityEmployeeRepo.save(
                activityEmployeeMapper.map(employee, activity)
        );
    }

    public void updateStatus(@NonNull UUID activityId, @NonNull String login, @NonNull EmployeeStatus status) {
        final ActivityEmployee employee = findByActivityIdAndEmployeeLogin(activityId, login);
        employee.setStatus(status);
        activityEmployeeRepo.save(employee);
    }

    private final static class ExceptionMessage {
        public static final String ACTIVITY_EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE = "Can't find the specified employee's activity";
    }
}
