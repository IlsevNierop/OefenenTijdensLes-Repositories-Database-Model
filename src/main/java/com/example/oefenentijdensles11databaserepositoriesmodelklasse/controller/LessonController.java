package com.example.oefenentijdensles11databaserepositoriesmodelklasse.controller;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.dto.LessonDto;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Lesson;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.CourseRepository;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.LessonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonController(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<Long> createLesson(@RequestBody LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setTopic(lessonDto.topic);
        lesson.setCourse(courseRepository.findById(lessonDto.courseId).get());
        lessonRepository.save(lesson);

        return new ResponseEntity<>(lessonDto.id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Lesson>> getAllLessons(){
        return new ResponseEntity<>(lessonRepository.findAll(), HttpStatus.OK);
    }
}
