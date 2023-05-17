package com.example.oefenentijdensles11databaserepositoriesmodelklasse.dto;

import java.util.ArrayList;

public class CourseDto {

    public Long id;

    public String title;

    public int sp;

    public ArrayList<Long> teacherIds;

    //array van longs maken met teacherIds - en daar doorheen loopen in je controller om die dan te koppelen.

    public ArrayList<String> lessonTopics = new ArrayList<>();

    public ArrayList<String> teacherNames = new ArrayList<>();
}
