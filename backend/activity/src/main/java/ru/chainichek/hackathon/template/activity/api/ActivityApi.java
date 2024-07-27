package ru.chainichek.hackathon.template.activity.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityRegistrationRequestDto;

import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping("v1/activity")
public interface ActivityApi {
    @GetMapping("/{activityId}")
    ResponseEntity<?> find(@PathVariable("activityId") UUID activityId);

    @GetMapping("/{login}")
    ResponseEntity<?> findByLogin(@PathVariable("login") @NotNull @Valid String login,
                                     @RequestParam(value = "startAt", required = false) LocalDateTime startAt,
                                     @RequestParam(value = "endAt", required = false) LocalDateTime endAt);

    @PostMapping("")
    ResponseEntity<?> create(@RequestParam("author") @NotBlank @Valid String author,
                             @RequestBody @NotNull @Valid ActivityRegistrationRequestDto activityRegistrationRequest);

    @PatchMapping("")
    ResponseEntity<?> update(@RequestParam("author") @NotBlank @Valid String author,
                             @RequestBody @NotNull @Valid ActivityRegistrationRequestDto activityRegistrationRequest);

    @DeleteMapping("/{activityId}")
    ResponseEntity<?> delete(@PathVariable("activityId") UUID activityId);
}
