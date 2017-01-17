package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by ja on 12.01.17.
 */

public interface ClassroomService {
    Classroom saveClassroom(Classroom classroom);
    void delete(Classroom classroom);
    Classroom updateByClassroomNumber(Classroom classroom);
    List<Classroom> getAll();
    Classroom getClassroomById(long id);
    Classroom getClassroomByNumber(int classNumber);
    Classroom getClassroomByName(String className);
    Set<Student> getClassroomStudentsByClassroomNumber(int classNumber);
    Set<Student> getClassroomStudentsByClassroomName(String userName);



}
