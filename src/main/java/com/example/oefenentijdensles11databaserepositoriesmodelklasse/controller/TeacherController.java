package com.example.oefenentijdensles11databaserepositoriesmodelklasse.controller;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Teacher;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository repos;

    @GetMapping
    public ResponseEntity<Iterable<Teacher>> getTeachers(){
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/before")
    public ResponseEntity<Iterable<Teacher>> getTeachersBefore(@RequestParam LocalDate date){
        return ResponseEntity.ok(repos.findByDobBefore(date));
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher t) {
        repos.save(t);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + t.getId()).toUriString());
        return ResponseEntity.created(uri).body(t);

    }
}
