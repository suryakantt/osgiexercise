package com.mysite.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.mysite.core.services.student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = StudentClassService.class)
public class StudentClassService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static List<student> studentList = new ArrayList<>();
    @Reference
    ClassConfigurationService classconfigurationservice;
    student s = new student("ash", 1, 18, 33);

    public void addStudent(student value) {
        studentList.add(value);
        LOGGER.info("added");

    }

    public void deleteStudent(int id) {
        studentList.removeIf(s -> s.getId() == id);
        LOGGER.info("removed" + id);

    }

    public boolean isStudentPassed(int id) {
        boolean flag=false;
        for (student i : studentList) {
            if (i.getId() == id)
                if (i.getMarks_obtained() > classconfigurationservice.minmarks)
                    flag=true;
                else
                    flag=false;

        }
        return flag;
    }

    public student getStudent(int id){
       return studentList.get(id);
    }

    public List getAllStudents(){
        return studentList;
    }
}



