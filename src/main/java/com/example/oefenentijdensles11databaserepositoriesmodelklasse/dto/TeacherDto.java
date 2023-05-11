package com.example.oefenentijdensles11databaserepositoriesmodelklasse.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TeacherDto {

    public Long id;
    @NotBlank(message="mag niet leeg zijn")
    public String firstName;

    @Size(min=3, max=128, message = "grootte moet tussen 3 en 128 tekens zijn")
    public String lastName;

    @Past
    public LocalDate dob;

    @Max(value=100000, message="test")
    public int salary;

}
