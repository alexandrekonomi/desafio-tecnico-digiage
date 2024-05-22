package com.example.service;

import com.example.model.Employee;
import com.example.payload.EmployeeDto;
import com.example.payload.PagedResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EmployeeService {
    ResponseEntity<?> createEmployee(EmployeeDto employeeDto);
    PagedResponse<EmployeeDto> getAll(int page, int size);
    Employee getById(UUID id);
    ResponseEntity<?> delete(UUID id);
    ResponseEntity<?> update(UUID id, EmployeeDto employeeDto);
}
