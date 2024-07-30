package ru.babim.template.activity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.babim.template.activity.dto.activity.ActivityDto;
import ru.babim.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.babim.template.activity.exception.ForbiddenException;
import ru.babim.template.activity.exception.NotFoundException;
import ru.babim.template.activity.mapper.ActivityMapper;
import ru.babim.template.activity.model.activity.Activity;
import ru.babim.template.activity.model.activity.ActivityStatus;
import ru.babim.template.activity.model.user.Role;
import ru.babim.template.activity.repo.ActivityRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ActivityService {
    private final ActivityRepo activityRepo;
    private final ActivityMapper activityMapper;

    public Activity findById(@NonNull UUID id) {
        return activityRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ACTIVITY_NOT_FOUND_EXCEPTION_MESSAGE, id.toString()));
    }

    public ActivityDto find(@NonNull UUID id) {
        return activityMapper.map(findById(id));
    }

    public List<ActivityDto> findAllByLogin(@NonNull String login,
                                            ActivityStatus status,
                                            LocalDateTime startAt,
                                            LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_DATE_RANGE_EXCEPTION_MESSAGE.formatted(startAt, endAt));
        }

        return activityRepo.findByEmployeeLoginAndStatusAndDateRange(login, status, startAt, endAt)
                .stream()
                .map(activityMapper::map)
                .toList();
    }

    @Transactional
    public ActivityDto create(@NonNull ActivityRegistrationRequestDto request,
                              @NonNull String author) {
        return activityMapper.map(
                activityRepo.save(
                        activityMapper.map(request, author)
                )
        );
    }

    @Transactional
    public void update(@NonNull UUID id,
                       @NonNull ActivityRegistrationRequestDto request,
                       @NonNull String author,
                       @NonNull Role role) {
        final Activity activity = findById(id);
        if (!activity.getAuthor().equals(author) || role != Role.ADMIN) {
            throw new ForbiddenException(ExceptionMessage.NO_ACCESS_EXCEPTION_MESSAGE);
        }
        activityRepo.save(activityMapper.map(activity, request));
    }

    @Transactional
    public void updateStatus(@NonNull Activity activity, @NonNull ActivityStatus status) {
        activity.setStatus(status);
        activityRepo.save(activity);
    }

    @Transactional
    public void delete(@NonNull UUID id,
                       @NonNull String author,
                       @NonNull Role role) {
        final Activity activity = findById(id);
        if (!activity.getAuthor().equals(author) || role != Role.ADMIN) {
            throw new ForbiddenException(ExceptionMessage.NO_ACCESS_EXCEPTION_MESSAGE);
        }

        activityRepo.delete(activity);
    }

    private final static class ExceptionMessage {
        public static final String ACTIVITY_NOT_FOUND_EXCEPTION_MESSAGE = "Can't find the specified activity";
        public static final String ILLEGAL_DATE_RANGE_EXCEPTION_MESSAGE = "Illegal date range: startAt must be after endAt; startAt = %s, endAt = %s";
        public static final String NO_ACCESS_EXCEPTION_MESSAGE = "No access to the specified activity";
    }
}