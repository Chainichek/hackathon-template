package ru.chainichek.hackathon.template.activity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.chainichek.hackathon.template.activity.api.ActivityApi;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityDto;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.chainichek.hackathon.template.activity.model.user.Role;
import ru.chainichek.hackathon.template.activity.service.ActivityService;

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
                                         LocalDateTime startAt,
                                         LocalDateTime endAt) {
        return ResponseEntity.ok(activityService.findAllByLogin(login, startAt, endAt));
    }

    @Override
    public ResponseEntity<?> create(String author,
                                    ActivityRegistrationRequestDto request) {
        final ActivityDto activity = activityService.create(request, author);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{activityId}")
                        .buildAndExpand(activity.id())
                        .toUri())
                .body(activity);
    }

    @Override
    public ResponseEntity<?> update(UUID activityId,
                                    String author,
                                    Role role,
                                    ActivityRegistrationRequestDto request) {
        activityService.update(activityId, request, author, role);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> delete(UUID activityId,
                                    String author,
                                    Role role) {
        activityService.delete(activityId, author, role);
        return ResponseEntity.noContent().build();
    }
}
