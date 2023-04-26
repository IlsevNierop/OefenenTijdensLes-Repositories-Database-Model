package com.example.oefenentijdensles11databaserepositoriesmodelklasse.controller;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Student;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository repos;

    @GetMapping
    public ResponseEntity<Iterable<Student>> getStudents(){
        return ResponseEntity.ok(repos.findAll());
    }
    @GetMapping("/name")
    public ResponseEntity<Iterable<Student>> getStudentsByNameSubstring(@RequestParam String substringName){
        return ResponseEntity.ok(repos.findByFullNameContainingIgnoreCase(substringName));
    }
    @GetMapping("/nameandsort")
    public ResponseEntity<Iterable<Student>> getStudentsByNameSubstringOrderByFullName(@RequestParam String substringName){
        return ResponseEntity.ok(repos.findByFullNameContainingIgnoreCaseOrderByFullName(substringName));
    }


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student s){
        repos.save(s);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + s.getStudentNr()).toUriString());
        return ResponseEntity.created(uri).body(s);
    }
}
