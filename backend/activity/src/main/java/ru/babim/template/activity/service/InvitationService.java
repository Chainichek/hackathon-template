package ru.babim.template.activity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.babim.template.activity.client.EmployeeClient;
import ru.babim.template.activity.dto.employee.EmployeeDto;
import ru.babim.template.activity.dto.employee.GroupDto;
import ru.babim.template.activity.exception.ForbiddenException;
import ru.babim.template.activity.exception.WrongStatusException;
import ru.babim.template.activity.model.activity.Activity;
import ru.babim.template.activity.model.activity.ActivityStatus;
import ru.babim.template.activity.model.activity.EmployeeStatus;
import ru.babim.template.activity.model.user.Role;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class InvitationService {
    private final EmployeeClient employeeClient;

    private final ActivityService activityService;
    private final ActivityEmployeeService activityEmployeeService;
    private final NotificationService notificationService;

    private Activity findForInvite(@NonNull UUID activityId,
                                   @NonNull String author,
                                   @NonNull Role role) {
        final @NonNull Activity activity = activityService.findById(activityId);
        if (activity.getStatus() != ActivityStatus.CREATED) {
            throw new WrongStatusException(ExceptionMessage.EXPECTED_CREATED_STATUS_ON_INVITE_EXCEPTION_MESSAGE);
        }
        if (!activity.getAuthor().equals(author) || role != Role.ADMIN) {
            throw new ForbiddenException(ExceptionMessage.NO_ACCESS_EXCEPTION_MESSAGE);
        }

        return activity;
    }

    private Activity findForStatusUpdate(@NonNull UUID activityId) {
        final @NonNull Activity activity = activityService.findById(activityId);
        if (activity.getStatus() != ActivityStatus.CREATED) {
            throw new WrongStatusException(ExceptionMessage.EXPECTED_CREATED_STATUS_ON_STATUS_UPDATE_EXCEPTION_MESSAGE);
        }

        return activity;
    }

    @Transactional
    public void inviteEmployees(@NonNull UUID activityId,
                                @NonNull String author,
                                @NonNull Role role,
                                @NonNull List<String> employeeLogins) {
        final Activity activity = findForInvite(activityId, author, role);

        final List<EmployeeDto> employees = employeeLogins.stream()
                .map(employeeClient::findEmployee)
                .toList();

        employees.forEach(employee -> {
            notificationService.notify(employee, activityId);
            activityEmployeeService.create(activity, employee);
        });
    }

    @Transactional
    public void inviteGroup(@NonNull UUID activityId,
                            @NonNull UUID groupId,
                            @NonNull String author,
                            @NonNull Role role) {
        final Activity activity = findForInvite(activityId, author, role);

        final GroupDto group = employeeClient.findGroup(groupId);

        group.employees().forEach(employee -> {
            notificationService.notify(employee, activityId);
            activityEmployeeService.create(activity, employee);
        });
    }

    @Transactional
    public void acceptInvite(@NonNull UUID activityId,
                             @NonNull String login) {
        final Activity activity = findForStatusUpdate(activityId);

        activityEmployeeService.updateStatus(activityId, login, EmployeeStatus.APPROVED);

        if (activity.getEmployees().stream().noneMatch(employee -> employee.getStatus() == EmployeeStatus.UNCHECKED)) {
            activityService.updateStatus(activity, ActivityStatus.CONFIRMED);
        }
    }

    @Transactional
    public void denyInvite(@NonNull UUID activityId,
                           @NonNull String login) {
        final Activity activity = findForStatusUpdate(activityId);

        activityEmployeeService.updateStatus(activityId, login, EmployeeStatus.REFUSED);

        if (activity.getEmployees().stream().noneMatch(employee -> employee.getStatus() == EmployeeStatus.UNCHECKED)) {
            activityService.updateStatus(activity, ActivityStatus.CONFIRMED);
        }
    }

    private static final class ExceptionMessage {
        public static final String EXPECTED_CREATED_STATUS_ON_INVITE_EXCEPTION_MESSAGE = "Can't invite for already confirmed activity";
        public static final String EXPECTED_CREATED_STATUS_ON_STATUS_UPDATE_EXCEPTION_MESSAGE = "Can't update status for already confirmed activity";
        public static final String NO_ACCESS_EXCEPTION_MESSAGE = "Can't invite because no access to the specified activity";
    }
}
