package ru.chainichek.hackathon.template.activity.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chainichek.hackathon.template.activity.dto.admin.ActivityDto;
import ru.chainichek.hackathon.template.activity.dto.util.ErrorMessage;
import ru.chainichek.hackathon.template.activity.dto.util.InternalErrorMessage;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityStatus;
import ru.chainichek.hackathon.template.activity.model.activity.EmployeeStatus;

import java.util.UUID;

@RequestMapping("${app.mvc.context-path}/admin")
@Tag(name = "Admin",
        description = """
                Contains operations for administrating activities.
                """)
public interface AdminApi {
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
    @GetMapping("/activity/{activityId}")
    ResponseEntity<?> findActivity(@PathVariable UUID activityId);


    @Operation(
            summary = "Updating an employee status in activity",
            description = "Updates an employee status by given activity ID and employee login with given status"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successful employee in activity update",
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
                            responseCode = "404",
                            description = "Employee in activity was not found",
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
    @PatchMapping("/activity/{activityId}/employee/{login}")
    ResponseEntity<?> updateEmployeeStatus(@PathVariable UUID activityId,
                                           @PathVariable String login,
                                           @RequestParam EmployeeStatus status);

    @Operation(
            summary = "Updating an activity status",
            description = "Updates an activity by given ID with given status"
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
    @PatchMapping("/activity/{activityId}")
    ResponseEntity<?> updateActivityStatus(@PathVariable UUID activityId,
                                           @RequestParam ActivityStatus status);
}
