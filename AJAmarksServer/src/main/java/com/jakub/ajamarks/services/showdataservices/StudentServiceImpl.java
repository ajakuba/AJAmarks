package com.jakub.ajamarks.services.showdataservices;

import com.google.common.base.Preconditions;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.MarkRepository;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ja on 15.01.17.
 */

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarkRepository markRepository;

    @Override
    public Student saveStudent(Student student) {
        Preconditions.checkArgument(student!=null, "student can't be null");
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(long studentId, Student student) {
        Preconditions.checkArgument(student!=null, "student can't be null");
        Preconditions.checkNotNull(student.getUserName(), "student name can't be null");
        Student foundStudent = studentRepository.findOne(studentId);
        student.setIdStudent(foundStudent.getIdStudent());
        return studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        Preconditions.checkArgument(student!=null, "student can't be null");
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAllOrderByLastNameDesc();
    }

    @Override
    public Student getStudentById(long id) {
        Preconditions.checkArgument(id>0, "id student can't be less than 1");
        return studentRepository.findOne(id);
    }

    @Override
    public Student getStudentByUserName(String userName) {
        Preconditions.checkArgument(userName!=null, "student's user name can't be null");
        return studentRepository.findByUserName(userName);
    }

    @Override
    public List<Student> getClassroomStudentsByClassroomNumberDescLastName(int classroomNumber) {
        Preconditions.checkArgument(classroomNumber > 0, "classroom number can't be less than 1");
        return studentRepository.findByClassroomClassroomNumber(classroomNumber);
    }

    @Override
    public List<Student> getClassroomStudentsByClassroomNameDescLastName(String classroomName) {
        Preconditions.checkArgument(classroomName!=null, "classroom name can't be null");
        return studentRepository.findByClassroomClassroomName(classroomName);
    }

    @Override
    public List<Student> getStudentsWithGivenMarkValue(int markValue) {
        Preconditions.checkArgument(markValue>=1 && markValue<=6, "mark mast be from 1 to 6 ");
        List<Student> studentList = new ArrayList<>();

        Mark mark = markRepository.findByMarkValueNamedQuery(markValue);

        studentRepository.findAll().forEach(student -> {
            if (student.getStudentMarks().contains(mark))
                studentList.add(student);
        });

        if (studentList.size() > 0)
            return studentList;
        else
            return Collections.emptyList();
    }

    @Override
    public List<Student> getStudentsWithoutGivenMarkValue(int markValue) {
        Preconditions.checkArgument(markValue>=1 && markValue<=6, "mark mast be from 1 to 6 ");
        List<Student> studentList = new ArrayList<>();

        Mark mark = markRepository.findByMarkValueNamedQuery(markValue);

        studentRepository.findAll().forEach(student -> {
            if (!student.getStudentMarks().contains(mark))
                studentList.add(student);
        });

        if (studentList.size() > 0)
            return studentList;
        else
            return Collections.emptyList();
    }


}
