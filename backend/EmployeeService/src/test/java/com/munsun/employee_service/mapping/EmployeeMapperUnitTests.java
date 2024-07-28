package com.munsun.employee_service.mapping;

import com.munsun.employee_service.dto.EmployeeInfoDto;
import com.munsun.employee_service.models.Employee;
import com.munsun.employee_service.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeMapperUnitTests {
    @Autowired
    private EmployeeMapper mapper;

    @DisplayName("Test map EmployeeInfoDto to Employee transient")
    @Test
    public void givenEmployeeInfoDto_whenMapToEmployee_thenReturnValidEmployeeTransient() {
        EmployeeInfoDto employeeInfo = TestUtils.getEmployeeInfoDto_Munir();
        Employee expectedTransientEmployee = TestUtils.getEmployeeTransient_Munir();

        Employee actualEmployee = mapper.map(employeeInfo);

        assertThat(actualEmployee)
                .usingRecursiveComparison()
                .ignoringFields(Employee.Fields.employeeId.name(),
                                Employee.Fields.role.name(),
                                Employee.Fields.isRemoved.name(),
                                Employee.Fields.password.name())
                .isEqualTo(expectedTransientEmployee);
    }

    @DisplayName("Test map persistent Employee to EmployeeInfoDto")
    @Test
    public void givenEmployeePersistent_whenMapToEmployeeInfoDto_thenReturnValidEmployeeInfoDto() {
        Employee persistentEmployee = TestUtils.getEmployeePersistent_Munir();
        EmployeeInfoDto expectedEmployeeInfo = TestUtils.getEmployeeInfoDto_Munir();

        EmployeeInfoDto actualEmployeeInfo = mapper.map(persistentEmployee);

        assertThat(actualEmployeeInfo)
                .usingRecursiveComparison()
                .isEqualTo(expectedEmployeeInfo);
    }
}
