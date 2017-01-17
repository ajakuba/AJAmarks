package com.jakub.ajamarks.services.statisticservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ja on 16.01.17.
 */

@Service
public class StudentStatisticServiceImpl implements StudentStatisticService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Double countAverageMark(String userName) {
        return studentRepository.findByUserName(userName).getMarkList()
                .stream()
                .mapToInt(Mark::getMarkValue)
                .average()
                .orElse(0);
    }

    @Override
    public Map<Integer, Student> getIntegerStudentMap(Classroom classroom) {
        Map<Integer, Student> integerStudentMap = new TreeMap<>();

        List<Student> studentOneClassroomList = studentRepository.findAll()
                .stream()
                .filter(student -> student.getClassroom().equals(classroom))
                .collect(Collectors.toList());

        studentOneClassroomList.forEach(student -> student.getMarkList().forEach(mark -> integerStudentMap.put(mark.getMarkValue(), student)));

        return integerStudentMap;

    }
}
