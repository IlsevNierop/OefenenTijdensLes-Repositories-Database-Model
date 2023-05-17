package com.example.oefenentijdensles11databaserepositoriesmodelklasse.controller;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.dto.CourseDto;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Course;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Teacher;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.CourseRepository;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.KeyAgreementSpi;
import java.util.ArrayList;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping
    public ResponseEntity<Long> createCourse(@RequestBody CourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setSp(courseDto.sp);
        ArrayList<Teacher> courseTeachers = new ArrayList<>();
        if (courseDto.teacherIds !=null ) {
            for (Long teacherId : courseDto.teacherIds) {
                Teacher teacher = teacherRepository.findById(teacherId).get();
                courseTeachers.add(teacher);
            }

            course.setTeachers(courseTeachers);
        }

        courseRepository.save(course);

        return new ResponseEntity<>(course.getId(), HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<Iterable<CourseDto>> getAllCourses() {
        ArrayList<CourseDto> courseDtos = new ArrayList<>();
        for (Course c : courseRepository.findAll()
        ) {
            CourseDto courseDto = new CourseDto();
            courseDto.id = c.getId();
            courseDto.title = c.getTitle();
            courseDto.sp = c.getSp();
            for (Teacher t : c.getTeachers()
            ) {
                courseDto.teacherNames.add(t.getFirstName());

            }
            courseDtos.add(courseDto);
        }

        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }


}
