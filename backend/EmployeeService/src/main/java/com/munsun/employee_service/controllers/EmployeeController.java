package com.munsun.employee_service.controllers;

import com.munsun.employee_service.dto.EmployeeInfoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/v1/employees")
public interface EmployeeController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNewEmployee(@RequestBody @Valid EmployeeInfoDto employeeInfoDto);

    @PatchMapping
    void changeEmployee(@RequestParam String login, @RequestBody @Valid EmployeeInfoDto newEmployeeInfoDto);

    @DeleteMapping
    void deleteEmployee(@RequestParam @NotBlank String login);

    @GetMapping
    EmployeeInfoDto getEmployee(@RequestParam @NotBlank String login);
}
