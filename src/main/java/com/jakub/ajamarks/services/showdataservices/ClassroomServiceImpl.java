package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by ja on 12.01.17.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;
    @Autowired
    StudentRepository studentRepository;


    public List<Classroom> getAll() {
        return null;
    }

    public Classroom getClassroomById(long id) {
        Classroom byClassroomId = classroomRepository.findOne(id);
        return byClassroomId;
    }

    public Classroom getClassroomByName(String name) {
        Classroom byClassroomName = classroomRepository.findByClassroomName(name);

        return byClassroomName;
    }

    public Classroom getClassroomByNumber(int classNumber) {
        Classroom byClassroomNumber = classroomRepository.findByClassroomNumber(classNumber);
        return byClassroomNumber;
    }

    public Set<Student> getClassroomStudentsByClassNumber(int classNumber) {
        Classroom byClassroomNumber = classroomRepository.findByClassroomNumber(classNumber);
        Set<Student> studentsInClassroom = byClassroomNumber.getStudentsInClassroom();
        return studentsInClassroom;
    }

    public Set<Student> getClassroomStudentsByClassName(String className) {
        Classroom byClassroomName = classroomRepository.findByClassroomName(className);
        Set<Student> studentsInClassroom = byClassroomName.getStudentsInClassroom();
        return studentsInClassroom;
    }

    public List<Student> getClassroomStudents(String userName) {
        return null;
    }


    public List<Student> getStudentsWithoutGivenMark(Mark mark) {

        //TODO
        return studentRepository.findAll();
    }

}
