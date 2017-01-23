package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ja on 15.01.17.
 */

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateByUserName(Student student) {
        Student byUserName = studentRepository.findByUserName(student.getUserName());
        student.setIdStudent(byUserName.getIdStudent());
        return studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student getStudentByUserName(String userName) {
        return studentRepository.findByUserName(userName);
    }

    @Override
    public Collection<Student> getAllByOrderByLastNameDesc(int classroomNumber) {
        return studentRepository.findAllOrderByLastNameDesc();
    }

    @Override
    public Collection<Student> getClassroomStudentsByClassroom(String classroomName) {
        return studentRepository.findByClassroomClassroomName(classroomName);
    }

    @Override
    public List<Student> getStudentsWithGivenMark(Mark givenMark) {
        List<Student> studentList = new ArrayList<>();

        studentRepository.findAll().forEach(student -> {
            if (student.getMarkList().contains(givenMark))
                studentList.add(student);
        });

        if (studentList.size() > 0)
            return studentList;
        else
            return Collections.emptyList();
    }

    @Override
    public List<Student> getStudentsWithoutGivenMark(Mark givenMark) {

        List<Student> studentList = new ArrayList<>();

        studentRepository.findAll().forEach(student -> {
            if (!student.getMarkList().contains(givenMark))
                studentList.add(student);
        });

        if (studentList.size() > 0)
            return studentList;
        else
            return Collections.emptyList();
    }


}
