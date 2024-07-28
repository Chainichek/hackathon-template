package ru.chainichek.hackathon.template.activity.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chainichek.hackathon.template.activity.model.user.Role;

import java.util.List;
import java.util.UUID;

@RequestMapping("${app.mvc.context-path}/invitations")
public interface InvitationApi {
    @PostMapping("/{activityId}")
    ResponseEntity<?> invite(@PathVariable UUID activityId,
                             @RequestParam("author") @NotBlank @Valid String author,
                             @RequestParam("role") @NotNull @Valid Role role,
                             @RequestBody @NotEmpty @Valid List<String> employeeLogins);

    @PostMapping("/{activityId}/group")
    ResponseEntity<?> inviteGroup(@PathVariable UUID activityId,
                                  @RequestParam("author") @NotBlank @Valid String author,
                                  @RequestParam("role") @NotNull @Valid Role role,
                                  @RequestParam UUID groupId);

    @PostMapping("/{activityId}/accept")
    ResponseEntity<?> accept(@PathVariable UUID activityId,
                             @RequestParam("login") @NotBlank String login);

    @PostMapping("/{activityId}/deny")
    ResponseEntity<?> deny(@PathVariable UUID activityId,
                           @RequestParam("login") @NotBlank String login);
}
