package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Employee;
import com.example.payload.EmployeeDto;
import com.example.payload.PagedResponse;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<?> createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setGender(employeeDto.getGender());

        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
    }

    @Override
    public PagedResponse<EmployeeDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);

        List<EmployeeDto> content = employees.getNumberOfElements() == 0
                ? Collections.emptyList()
                : employees.getContent()
                .stream()
                .map(Employee::convertEmployeeToEmployeeDto)
                .toList();
        return new PagedResponse<>(
                content,
                employees.getNumber(),
                employees.getSize(),
                employees.getTotalElements(),
                employees.getTotalPages(),
                employees.isLast()
        );
    }

    @Override
    public Employee getById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return employee;
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employeeRepository.delete(employee);

        return ResponseEntity.ok("Employee deleted successfully");
    }

    @Override
    public ResponseEntity<?> update(UUID id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        String firstname = StringUtils.stripToNull(employeeDto.getFirstName());
        if (Objects.nonNull(firstname)) {
            employee.setFirstName(firstname);
        }

        String lastName = StringUtils.stripToNull(employeeDto.getLastName());
        if (Objects.nonNull(lastName)) {
            employee.setLastName(lastName);
        }

        String gender = StringUtils.stripToNull(employeeDto.getGender());
        if (Objects.nonNull(gender)) {
            employee.setGender(gender);
        }

        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body("employee updated successfully");
    }
}

