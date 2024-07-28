package ru.chainichek.hackathon.template.activity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chainichek.hackathon.template.activity.api.InvitationApi;

import java.util.List;
import java.util.UUID;

@RestController
public class InvitationController implements InvitationApi {
    @Override
    public ResponseEntity<?> invite(UUID activityId, List<String> employeeLogins) {
        return null;
    }

    @Override
    public ResponseEntity<?> inviteGroup(UUID activityId, UUID group) {
        return null;
    }

    @Override
    public ResponseEntity<?> acceptInvite(UUID activityId) {
        return null;
    }

    @Override
    public ResponseEntity<?> denyInvite(UUID activityId) {
        return null;
    }
}
