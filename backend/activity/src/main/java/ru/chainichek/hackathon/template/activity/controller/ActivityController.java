package ru.chainichek.hackathon.template.activity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chainichek.hackathon.template.activity.api.ActivityApi;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityRegistrationRequestDto;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class ActivityController implements ActivityApi {
    @Override
    public ResponseEntity<?> find(UUID activityId) {
        return null;
    }

    @Override
    public ResponseEntity<?> findByLogin(String login, LocalDateTime startAt, LocalDateTime endAt) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(String author, ActivityRegistrationRequestDto activityRegistrationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(String author, ActivityRegistrationRequestDto activityRegistrationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(UUID activityId, String author) {
        return null;
    }
}
