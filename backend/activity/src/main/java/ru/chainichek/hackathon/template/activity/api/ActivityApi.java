package ru.chainichek.hackathon.template.activity.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityDto;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.chainichek.hackathon.template.activity.dto.util.ErrorMessage;
import ru.chainichek.hackathon.template.activity.dto.util.InternalErrorMessage;
import ru.chainichek.hackathon.template.activity.model.user.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping("${app.mvc.context-path}/activity")
public interface ActivityApi {
    @Operation(
            summary = "Calculation of possible loan terms",
            description = "Calculates possible loan terms based on the provided request data"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "List of possible loan terms",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ActivityDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Statement was not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = InternalErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/{activityId}")
    ResponseEntity<?> find(@PathVariable("activityId") UUID activityId);

    @GetMapping("")
    ResponseEntity<?> findByLogin(@RequestParam("login") @NotBlank @Valid String login,
                                  @RequestParam(value = "startAt", required = false) LocalDateTime startAt,
                                  @RequestParam(value = "endAt", required = false) LocalDateTime endAt);

    @PostMapping("")
    ResponseEntity<?> create(@RequestParam("author") @NotBlank @Valid String author,
                             @RequestBody @NotNull @Valid ActivityRegistrationRequestDto request);

    @PatchMapping("/{activityId}")
    ResponseEntity<?> update(@PathVariable("activityId") UUID activityId,
                             @RequestParam("author") @NotBlank @Valid String author,
                             @RequestParam("role") @NotBlank @Valid Role role,
                             @RequestBody @NotNull @Valid ActivityRegistrationRequestDto request);

    @DeleteMapping("/{activityId}")
    ResponseEntity<?> delete(@PathVariable("activityId") UUID activityId,
                             @RequestParam("author") @NotBlank @Valid String author,
                             @RequestParam("role") @NotNull @Valid Role role);
}
