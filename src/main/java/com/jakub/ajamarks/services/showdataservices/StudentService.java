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
    Student updateByUserName(Student student);
    void delete(Student student);
    List<Student> getAll();
    Student getStudentById(long id);
    Student getStudentByUserName(String userName);
    Collection<Student> getAllByOrderByLastNameDesc(int classroomNumber);
    Collection<Student> getClassroomStudentsByClassroom(String classroomName);
    List<Student> getStudentsWithGivenMark(Mark givenMark);
    List<Student> getStudentsWithoutGivenMark(Mark givenMark);
}
