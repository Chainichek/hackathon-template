package ru.chainichek.hackathon.template.activity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chainichek.hackathon.template.activity.api.InvitationApi;
import ru.chainichek.hackathon.template.activity.model.user.Role;
import ru.chainichek.hackathon.template.activity.service.InvitationService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class InvitationController implements InvitationApi {
    private final InvitationService invitationService;

    @Override
    public ResponseEntity<?> invite(UUID activityId,
                                    String author,
                                    Role role,
                                    List<String> employeeLogins) {
        invitationService.inviteEmployees(activityId, author, role, employeeLogins);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> inviteGroup(UUID activityId,
                                         String author,
                                         Role role,
                                         UUID groupId) {
        invitationService.inviteGroup(activityId, groupId, author, role);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> accept(UUID activityId,
                                    String login) {
        invitationService.acceptInvite(activityId, login);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deny(UUID activityId,
                                  String login) {
        invitationService.denyInvite(activityId, login);
        return ResponseEntity.ok().build();
    }
}
