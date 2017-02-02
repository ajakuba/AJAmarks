package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;

import java.util.Collection;
import java.util.List;

/**
 * Created by ja on 15.01.17.
 */

public interface StudentService {
    Student saveStudent(Student student);
    Student updateByUserName(long studentId, Student student);
    void delete(Student student);
    List<Student> getAll();
    Student getStudentById(long id);
    Student getStudentByUserName(String userName);
    List<Student> getClassroomStudentsByClassroomNumberDescLastName(int classroomNumber);
    List<Student> getClassroomStudentsByClassroomNameDescLastName(String classroomName);
    List<Student> getStudentsWithGivenMarkValue(int markValue);
    List<Student> getStudentsWithoutGivenMarkValue(int markValue);
}
