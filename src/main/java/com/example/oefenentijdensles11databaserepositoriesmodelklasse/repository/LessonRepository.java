package com.example.oefenentijdensles11databaserepositoriesmodelklasse.repository;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
