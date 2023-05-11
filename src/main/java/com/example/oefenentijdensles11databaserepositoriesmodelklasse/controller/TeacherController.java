package com.example.oefenentijdensles11databaserepositoriesmodelklasse.controller;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.dto.TeacherDto;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Teacher;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.TeacherRepository;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

//    @Autowired
//    private TeacherRepository repos;

    private final TeacherService teacherService;

    public TeacherController (TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<TeacherDto>> getAllTeachers(){
        return new ResponseEntity(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id){
        TeacherDto teacherDto = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacherDto);
    }
//
//    @GetMapping("/before")
//    public ResponseEntity<Iterable<Teacher>> getTeachersBefore(@RequestParam LocalDate date){
//        return ResponseEntity.ok(repos.findByDobBefore(date));
//    }
//    @GetMapping("/age")
//    public ResponseEntity<Iterable<Teacher>> getTeachersByAge(@RequestParam int age){
//        LocalDate date =  LocalDate.now().plusYears(-age);
//        return ResponseEntity.ok(repos.findByDobBefore(date));
//    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : bindingResult.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }
        else {
            Long newId = teacherService.createTeacher(teacherDto);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + newId).toUriString());
            return ResponseEntity.created(uri).body(newId);
        }

    }
}
