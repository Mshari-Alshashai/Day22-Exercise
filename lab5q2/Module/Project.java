package com.example.lab5q2.Module;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "The ID is Empty")
    @Size(min = 3,message = "The ID should be more than 3 numbers")
    private String ID;
    @NotEmpty(message = "The title is empty")
    @Size(min = 8,message = "The title should have more than 8 characters")
    private String title;
    @NotEmpty(message = "The description is empty")
    @Size(min = 15,message = "The description should have more than 15 characters")
    private String description;
    @NotEmpty(message = "The status is empty")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$",message = "Status must be one of the following: Not Started, In Progress, or Completed.")
    private String status;
    @NotEmpty(message = "The company name is empty")
    @Size(min = 7,message = "The company name should have more than 6 characters")
    private String companyName;
}
