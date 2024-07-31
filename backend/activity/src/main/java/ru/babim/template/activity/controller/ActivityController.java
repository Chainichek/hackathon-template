package ru.babim.template.activity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.babim.template.activity.api.ActivityApi;
import ru.babim.template.activity.dto.activity.ActivityDto;
import ru.babim.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.babim.template.activity.model.activity.ActivityStatus;
import ru.babim.template.activity.service.ActivityService;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ActivityController implements ActivityApi {
    private final ActivityService activityService;

    @Override
    public ResponseEntity<?> find(UUID activityId) {
        return ResponseEntity.ok(activityService.find(activityId));
    }

    @Override
    public ResponseEntity<?> findByLogin(String login,
                                         ActivityStatus status,
                                         LocalDateTime startAt,
                                         LocalDateTime endAt) {
        return ResponseEntity.ok(activityService.findAllByLogin(login, status, startAt, endAt));
    }

    @Override
    public ResponseEntity<?> create(ActivityRegistrationRequestDto request,
                                    Authentication authentication) {
        final ActivityDto activity = activityService.create(request, authentication);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{activityId}")
                        .buildAndExpand(activity.id())
                        .toUri())
                .body(activity);
    }

    @Override
    public ResponseEntity<?> update(UUID activityId,
                                    ActivityRegistrationRequestDto request,
                                    Authentication authentication) {
        activityService.update(activityId, request, authentication);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> delete(UUID activityId,
                                    Authentication authentication) {
        activityService.delete(activityId, authentication);
        return ResponseEntity.noContent().build();
    }
}
