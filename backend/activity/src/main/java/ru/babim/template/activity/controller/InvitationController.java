package ru.babim.template.activity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import ru.babim.template.activity.api.InvitationApi;
import ru.babim.template.activity.service.InvitationService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class InvitationController implements InvitationApi {
    private final InvitationService invitationService;

    @Override
    public ResponseEntity<?> invite(UUID activityId,
                                    List<String> employeeLogins,
                                    Authentication authentication) {
        invitationService.inviteEmployees(activityId, employeeLogins, authentication);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> inviteGroup(UUID activityId,
                                         UUID groupId,
                                         Authentication authentication) {
        invitationService.inviteGroup(activityId, groupId, authentication);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> accept(UUID activityId,
                                    Authentication authentication) {
        invitationService.acceptInvite(activityId, authentication);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deny(UUID activityId,
                                  Authentication authentication) {
        invitationService.denyInvite(activityId, authentication);
        return ResponseEntity.ok().build();
    }
}
