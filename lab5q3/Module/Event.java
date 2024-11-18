package com.example.lab5q3.Module;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "The ID is empty")
    @Size(min = 3,message = "The ID should be more than 2 numbers")
    private String ID;
    @NotEmpty(message = "The description is empty")
    @Size(min = 16,message = "The description should have more than 15 characters")
    private String description;
    @NotNull(message = "The Capacity is null")
    @Digits(integer = 5,fraction = 0,message = "The capacity should be a number with 5 digits maximum")
    @Min(value = 25,message = "The Capacity should be more than 25")
    private int capacity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
