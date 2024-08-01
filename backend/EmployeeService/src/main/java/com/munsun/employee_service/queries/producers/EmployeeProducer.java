package com.munsun.employee_service.queries.producers;

import com.munsun.employee_service.queries.payload.EmployeeCreationMessage;

public interface EmployeeProducer {
    void sendSuccessCreateEmployee(EmployeeCreationMessage employeeCreationMessage);
}
