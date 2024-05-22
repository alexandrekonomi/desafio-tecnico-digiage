package com.example.model;

import com.example.payload.EmployeeDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tab_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String gender;


    public static EmployeeDto convertEmployeeToEmployeeDto(Employee employee) {
    return EmployeeDto.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .gender(employee.getGender())
            .build();
    }
}
