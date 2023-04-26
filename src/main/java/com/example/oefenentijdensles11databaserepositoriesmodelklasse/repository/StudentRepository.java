package com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Iterable<Student> findByFullNameContainingIgnoreCase(String substring);

    Iterable<Student> findByFullNameContainingIgnoreCaseOrderByFullName(String substring);
}
