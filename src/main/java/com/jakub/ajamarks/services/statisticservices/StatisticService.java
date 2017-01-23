package com.jakub.ajamarks.services.statisticservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ja on 16.01.17.
 */
public interface StatisticService {

    Double countAverageMarkForUser(String userName);
    Double countAverageMarkForClassroom(String classroomName);
    Double countAverageMarkForSchool();

    boolean isStudentAverageHigherThenInClassroom(String userName);
    boolean isStudentAverageHigherThenInSchool(String userName);

    List<Student> getStudentsAboveSchoolAverage();
    List<Student> getStudentsAboveStudentAverage(String UserName);
    int countHowManyStudentsAboveSchoolAverage();
    int countHowManyStudentsAboveStudentAverage(String userName);
    Map <Double, TreeSet<Student>> sortStudentsByAverageAsc();

    Map<Mark, Integer> countQuantityOfEachMarkForSchool();
    Map<Mark, Set<Student>> getFromSchoolMarkStudentListMap();
    Map<Mark, Set<Student>> getFromClassroomMarkStudentListMap(String classRoomName);


}
