package com.example.controller;

import com.example.model.Employee;
import com.example.payload.EmployeeDto;
import com.example.payload.PagedResponse;
import com.example.service.EmployeeService;
import com.example.utils.AppConstants;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @JsonView(EmployeeDto.EmployeeView.EmployeePost.class) EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping
    public PagedResponse<EmployeeDto> getAll(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                             @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return employeeService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(value = "id") UUID id) {
        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") UUID id,
                                    @RequestBody @JsonView(EmployeeDto.EmployeeView.EmployeePut.class) EmployeeDto employeeDto) {
        return employeeService.update(id, employeeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        return employeeService.delete(id);
    }
}
