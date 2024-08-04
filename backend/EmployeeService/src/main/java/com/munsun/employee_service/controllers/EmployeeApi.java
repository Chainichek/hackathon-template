package com.munsun.employee_service.controllers;

import com.munsun.employee_service.dto.EmployeeInfoDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name="Employee controller")
public interface EmployeeApi {

    void createNewEmployee(@RequestBody @Valid EmployeeInfoDto employeeInfoDto);

    void changeEmployee(@RequestParam String login, @RequestBody @Valid EmployeeInfoDto newEmployeeInfoDto);

    void deleteEmployee(@RequestParam @NotBlank String login);

    EmployeeInfoDto getEmployee(@RequestParam @NotBlank String login);
}
