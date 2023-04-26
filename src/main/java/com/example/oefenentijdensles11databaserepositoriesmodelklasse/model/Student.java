package com.example.oefenentijdensles11databaserepositoriesmodelklasse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "students")
public class Student {

    @Id
    @GeneratedValue
    private Long studentNr;

    private String fullName;
    private int phoneNumber;

    public Long getStudentNr() {
        return studentNr;
    }

    public void setStudentNr(Long studentNr) {
        this.studentNr = studentNr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
