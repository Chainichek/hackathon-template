package ru.babim.template.activity.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.babim.lib.logger.annotation.ControllerLoggable;
import ru.babim.template.activity.dto.activity.ActivityDto;
import ru.babim.template.activity.dto.activity.ActivityRegistrationRequestDto;
import ru.babim.template.activity.dto.util.ErrorMessage;
import ru.babim.template.activity.dto.util.InternalErrorMessage;
import ru.babim.template.activity.model.activity.ActivityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping("${app.mvc.context-path}/activities")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Activities",
        description = """
                Contains operations for CRUD methods and for getting a list of employee activities. Also can remind about upcoming activities.
                """)
@ControllerLoggable
public interface ActivityApi {
    @Operation(
            summary = "Searching for an activity",
            description = "Searches for an activity by given ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Activity with given ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ActivityDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Activity was not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
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

    @Operation(
            summary = "Searching and reminding for an activities",
            description = "Searches for an activities by given user's login. Search parameters can be configured through date or status"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of activities by given parameters",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ActivityDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = InternalErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("")
    ResponseEntity<?> findByLogin(@RequestParam("login") @NotBlank @Valid String login,
                                  @RequestParam(value = "status", required = false) ActivityStatus status,
                                  @RequestParam(value = "startAt", required = false) LocalDateTime startAt,
                                  @RequestParam(value = "endAt", required = false) LocalDateTime endAt);

    @Operation(
            summary = "Creating an activity",
            description = "Creates an activity by given parameters"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "New activity",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ActivityDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = InternalErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping("")
    ResponseEntity<?> create(@RequestBody @NotNull @Valid ActivityRegistrationRequestDto request,
                             Authentication authentication);

    @Operation(
            summary = "Updating an activity",
            description = "Updates an activity by given ID with given parameters"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successful activity update",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema()
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No access for that activity",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Activity was not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = InternalErrorMessage.class)
                            )
                    )
            }
    )
    @PatchMapping("/{activityId}")
    ResponseEntity<?> update(@PathVariable("activityId") UUID activityId,
                             @RequestBody @NotNull @Valid ActivityRegistrationRequestDto request,
                             Authentication authentication);

    @Operation(
            summary = "Deleting an activity",
            description = "Deletes an activity by given ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful activity creation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema()
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No access for that activity",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Activity was not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = InternalErrorMessage.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{activityId}")
    ResponseEntity<?> delete(@PathVariable("activityId") UUID activityId,
                             Authentication authentication);
}
