package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by ja on 12.01.17.
 */
@Service
public interface ClassroomService {

    List<Classroom> getAll();
    Classroom getClassroomById(long id);
    Classroom getClassroomByName(String className);
    Classroom getClassroomByNumber(int classNumber);
    Collection<Student> getClassroomStudentsByClassNumber(int classNumber);
    Collection<Student> getClassroomStudentsByClassName(String userName);
    List<Student> getStudentsWithoutGivenMark(Mark givenMark);

}
