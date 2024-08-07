package ru.babim.template.helloworld.hellomicroservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.babim.template.helloworld.hellomicroservice.dto.name.NameDto;
import ru.babim.template.helloworld.hellomicroservice.dto.util.ErrorMessage;
import ru.babim.template.helloworld.hellomicroservice.dto.util.InternalErrorMessage;

@RequestMapping("/hello-world")
@Tag(name = "Hello World",
        description = """
                Contains operations for greeting you!
                """)
public interface HelloApi {
        @Operation(
                summary = "Greeting user",
                description = "Greets user with simple message"
        )
        @ApiResponses(
                value = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "Simple greeting",
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = String.class)
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
    @GetMapping("/")
    ResponseEntity<?> hello();

    @Operation(
            summary = "Greeting user by name",
            description = "Greets user with simple message including his name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Simple greeting with name",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
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
    @GetMapping("/{name}")
    ResponseEntity<?> hello(@PathVariable("name") String name);

    @Operation(
            summary = "Greeting user by full name",
            description = "Greets user with simple message including his full name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Simple greeting with full name",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
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
    @PostMapping("/")
    ResponseEntity<?> hello(@RequestBody NameDto name);
}
