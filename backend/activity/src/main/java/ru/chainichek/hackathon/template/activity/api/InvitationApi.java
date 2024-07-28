package ru.chainichek.hackathon.template.activity.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequestMapping("${app.mvc.context-path}/invitation")
public interface InvitationApi {
    @PostMapping("/{activityId}")
    ResponseEntity<?> invite(@PathVariable UUID activityId,
                             @RequestBody @NotEmpty @Valid List<String> employeeLogins);

    @PostMapping("/{activityId}/group")
    ResponseEntity<?> inviteGroup(@PathVariable UUID activityId,
                                  @RequestParam UUID groupId);

    @PatchMapping("/{activityId}/accept")
    ResponseEntity<?> acceptInvite(@PathVariable UUID activityId);

    @PatchMapping("/{activityId}/deny")
    ResponseEntity<?> denyInvite(@PathVariable UUID activityId);
}
