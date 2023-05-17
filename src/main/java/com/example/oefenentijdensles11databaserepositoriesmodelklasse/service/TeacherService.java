package com.example.oefenentijdensles11databaserepositoriesmodelklasse.service;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.dto.TeacherDto;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.exception.NameDuplicateException;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.exception.ResourceNotFoundException;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Course;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Teacher;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.CourseRepository;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repos;
    private final CourseRepository courseRepository;


    // hiermee dwing je dat als er een teacherservice wordt aangemaakt, dat er dan ook een teacher repository wordt aangemaakt.
   public TeacherService(TeacherRepository repos, CourseRepository courseRepository){
       this.repos = repos;
       this.courseRepository = courseRepository;
   }


    public Long createTeacher(TeacherDto teacherDto) throws NameDuplicateException {
       Optional<Teacher> optionalTeacher = Optional.ofNullable(repos.findByFirstNameAndLastName(teacherDto.firstName, teacherDto.lastName));
       if (!optionalTeacher.isEmpty()){
           throw new NameDuplicateException("This teacher already exists");
       }
       // de niet efficiente manier:
//       Iterable<Teacher> allTeachers = repos.findAll();
//
//        for (Teacher t: allTeachers) {
//            if (t.getFirstName().equals(teacherDto.firstName) && t.getLastName().equals(teacherDto.lastName)){
//                throw new NameDuplicateException("This teacher already exists");
//            }
//        }
       Teacher teacher = new Teacher();

       teacher.setFirstName(teacherDto.firstName);
       teacher.setLastName(teacherDto.lastName);
       teacher.setDob(teacherDto.dob);
       teacher.setSalary(teacherDto.salary);

       // deze werkt nog niet - de andere kant op werkt het wel
        ArrayList<Course> teacherCourses = new ArrayList<>();

        if (teacherDto.courseIds != null) {

            for (Long courseId : teacherDto.courseIds) {
                Course course = courseRepository.findById(courseId).get();
                teacherCourses.add(course);
            }

            teacher.setCourses(teacherCourses);
        }


       repos.save(teacher);

       return teacher.getId();

    }

    public TeacherDto getTeacherById(Long id) throws ResourceNotFoundException {
       Teacher t = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

       TeacherDto teacherDto = new TeacherDto();
       teacherDto.id = t.getId();
       teacherDto.firstName= t.getFirstName();
       teacherDto.lastName= t.getLastName();
       teacherDto.dob= t.getDob();
       teacherDto.salary= t.getSalary();

        for (Course c: t.getCourses()
             ) {
            teacherDto.courseTitles.add(c.getTitle());
        }
       return teacherDto;
    }

    public List<TeacherDto> getAllTeachers() {
       ArrayList<TeacherDto> teacherDtoArrayList = new ArrayList<>();
       Iterable<Teacher> allTeachers = repos.findAll();
        for (Teacher t: allTeachers
             ) {
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.id = t.getId();
            teacherDto.firstName= t.getFirstName();
            teacherDto.lastName= t.getLastName();
            teacherDto.dob= t.getDob();
            teacherDto.salary= t.getSalary();
            for (Course c: t.getCourses()
            ) {
                teacherDto.courseTitles.add(c.getTitle());
            }
            teacherDtoArrayList.add(teacherDto);
        }
        return teacherDtoArrayList;
    }


}
