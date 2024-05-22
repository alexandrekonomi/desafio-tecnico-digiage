package com.example.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    public interface EmployeeView {
        interface EmployeePost {

        }
        interface EmployeePut {

        }
    }

    private UUID id;
    @JsonView({EmployeeDto.EmployeeView.EmployeePost.class, EmployeeDto.EmployeeView.EmployeePut.class})
    private String firstName;
    @JsonView({EmployeeDto.EmployeeView.EmployeePost.class, EmployeeDto.EmployeeView.EmployeePut.class})
    private String lastName;
    @JsonView({EmployeeDto.EmployeeView.EmployeePost.class, EmployeeDto.EmployeeView.EmployeePut.class})
    private String gender;
}
