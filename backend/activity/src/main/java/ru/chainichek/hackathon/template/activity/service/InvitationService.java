package ru.chainichek.hackathon.template.activity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chainichek.hackathon.template.activity.client.EmployeeClient;
import ru.chainichek.hackathon.template.activity.dto.employee.EmployeeDto;
import ru.chainichek.hackathon.template.activity.dto.employee.GroupDto;
import ru.chainichek.hackathon.template.activity.exception.ForbiddenException;
import ru.chainichek.hackathon.template.activity.model.activity.Activity;
import ru.chainichek.hackathon.template.activity.model.activity.EmployeeStatus;
import ru.chainichek.hackathon.template.activity.model.user.Role;

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

    @Transactional
    public void inviteEmployees(@NonNull UUID activityId,
                                @NonNull String author,
                                @NonNull Role role,
                                @NonNull List<String> employeeLogins) {
        final @NonNull Activity activity = activityService.findById(activityId);

        if (!activity.getAuthor().equals(author) || role != Role.ADMIN) {
            throw new ForbiddenException(ExceptionMessage.NO_ACCESS_EXCEPTION_MESSAGE);
        }

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
        final @NonNull Activity activity = activityService.findById(activityId);

        if (!activity.getAuthor().equals(author) || role != Role.ADMIN) {
            throw new ForbiddenException(ExceptionMessage.NO_ACCESS_EXCEPTION_MESSAGE);
        }

        final GroupDto group = employeeClient.findGroup(groupId);

        group.employees().forEach(employee -> {
            notificationService.notify(employee, activityId);
            activityEmployeeService.create(activity, employee);
        });
    }

    @Transactional
    public void acceptInvite(@NonNull UUID activityId,
                             @NonNull String login) {
        activityEmployeeService.updateStatus(activityId, login, EmployeeStatus.APPROVED);
    }

    @Transactional
    public void denyInvite(@NonNull UUID activityId,
                           @NonNull String login) {
        activityEmployeeService.updateStatus(activityId, login, EmployeeStatus.REFUSED);
    }

    private static final class ExceptionMessage {
        public static final String NO_ACCESS_EXCEPTION_MESSAGE = "Can't invite because no access to the specified activity";
    }
}
