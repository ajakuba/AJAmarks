package com.jakub.ajamarks.services.statisticservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;

import java.util.Map;

/**
 * Created by ja on 16.01.17.
 */
public interface StudentStatisticService {

    Double countAverageMark(String userName);
    Map<Integer, Student> getIntegerStudentMap(Classroom classroom);


}
