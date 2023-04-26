package com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Iterable<Teacher> findByDobBefore(LocalDate date);

}
