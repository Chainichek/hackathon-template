package com.munsun.employee_service.controllers;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RequestMapping("/v1/groups")
public interface EmployeeGroupController {
    @GetMapping("/{groupsId}")
    EmployeeGroupDto getGroupEmployee(@PathVariable @NotNull UUID groupId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeGroupDto createNewGroupEmployee(@RequestParam @NotBlank String title,
                                           @RequestParam @NotBlank String description);

    @PatchMapping("/{groupId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeGroupDto addEmployeeGroup(@PathVariable @NotNull UUID groupId,
                                      @RequestParam @NotBlank String login);

    @DeleteMapping("/{groupsId}")
    void deleteEmployee(@PathVariable @NotNull UUID groupId);
}
