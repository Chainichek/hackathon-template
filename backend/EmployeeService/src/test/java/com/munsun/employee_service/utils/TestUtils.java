package com.munsun.employee_service.utils;

import com.munsun.employee_service.dto.EmployeeInfoDto;
import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import com.munsun.employee_service.dto.request.enums.Role;
import com.munsun.employee_service.models.Employee;
import com.munsun.employee_service.models.EmployeeGroup;

import java.util.List;
import java.util.UUID;

public class TestUtils {
    public static EmployeeInfoDto getEmployeeInfoDto_Munir() {
        return new EmployeeInfoDto(
                "munsun",
                "munsun12345",
                "Munir",
                "Sunchalyaev",
                "msunchalyaev@gmail.com",
                Role.EMPLOYEE
        );
    }

    public static Employee getEmployeeTransient_Munir() {
        EmployeeInfoDto employeeInfo = getEmployeeInfoDto_Munir();
        return Employee.builder()
                .name(employeeInfo.name())
                .lastname(employeeInfo.lastname())
                .email(employeeInfo.email())
                .login(employeeInfo.login())
                .build();
    }

    public static EmployeeInfoDto getEmployeeInfoDto_Andrey() {
        return new EmployeeInfoDto(
                "andron12297",
                "Andrey123456",
                "Chan",
                "Chen",
                "andrey1997@yandex.ru",
                Role.EMPLOYEE
        );
    }

    public static Employee getEmployeeTransient_Andrey() {
        return Employee.builder()
                .name("Andrey")
                .lastname("Chan")
                .email("andrey1997@yandex.ru")
                .login("andron12297")
                .build();
    }

    public static Employee getEmployeePersistent_Andrey() {
        Employee transientEmployee = getEmployeeTransient_Andrey();
            transientEmployee.setEmployeeId(UUID.randomUUID());
        return transientEmployee;
    }

    public static Employee getEmployeePersistent_Munir() {
        Employee transientEmployee = getEmployeeTransient_Munir();
            transientEmployee.setEmployeeId(UUID.randomUUID());
        return transientEmployee;
    }

    public static EmployeeGroup getEmployeeGroupTransient_1() {
        return EmployeeGroup.builder()
                .title("test title")
                .description("test description")
                .employees(List.of(
                        getEmployeeTransient_Munir(),
                        getEmployeeTransient_Andrey()
                ))
                .isRemoved(false)
                .build();
    }

    public static EmployeeGroup getEmployeeGroupPersistent_1() {
        return EmployeeGroup.builder()
                .groupId(UUID.randomUUID())
                .title("test title")
                .description("test description")
                .employees(List.of(
                        getEmployeePersistent_Munir(),
                        getEmployeePersistent_Andrey()
                ))
                .isRemoved(false)
                .build();
    }

    public static EmployeeGroupDto getEmployeeGroupDto_1() {
        EmployeeGroup employeeGroup = getEmployeeGroupPersistent_1();
        return new EmployeeGroupDto(
                employeeGroup.getGroupId(),
                employeeGroup.getTitle(),
                employeeGroup.getDescription(),
                List.of(getEmployeeInfoDto_Munir(), getEmployeeInfoDto_Andrey())
        );
    }
}
