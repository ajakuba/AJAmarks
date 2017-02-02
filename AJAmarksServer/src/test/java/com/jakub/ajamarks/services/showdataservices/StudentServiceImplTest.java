package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.MarkRepository;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by ja on 24.01.17.
 */
public class StudentServiceImplTest {

    private StudentServiceImpl studentServiceImpl;
    private Student student1;
    private List<Student> studentList;
    private Classroom classroom;
    private Mark mark;


    @Before
    public void testSetUp() throws Exception {

        studentServiceImpl = new StudentServiceImpl();
        studentServiceImpl.studentRepository = mock(StudentRepository.class);
        studentServiceImpl.markRepository = mock(MarkRepository.class);

        student1 = new Student();
        student1.setIdStudent(1);
        student1.setUserName("aja");
        student1.setFirstName("Jakub");
        student1.setLastName("Ja");

        classroom = new Classroom();
        classroom.setClassroomNumber(1);
        classroom.setClassroomName("Pierwsza");
        student1.setClassroom(classroom);

        mark = new Mark();
        mark.setMarkValue(5);
        mark.setMarkName("bdb");
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark);
        student1.setStudentMarks(markList);

        studentList = new ArrayList<>();
        studentList.add(student1);

    }

    @Test
    public void saveStudentTest() {
        //given
        when(studentServiceImpl.studentRepository.save(student1)).thenReturn(student1);
        //when
        studentServiceImpl.saveStudent(student1);
        //then
        verify(studentServiceImpl.studentRepository).save(student1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void saveStudent_IllegalArgumentExceptionTest() {
        //given
        Student student3 = null;
        //when
        studentServiceImpl.saveStudent(student3);
        //then
    }


    @Test
    public void updateByUserNameTest() {
        //given
        when(studentServiceImpl.studentRepository.findByUserName(student1.getUserName())).thenReturn(student1);
        //when
        studentServiceImpl.updateByUserName(student1);
        //then
        verify(studentServiceImpl.studentRepository).findByUserName(student1.getUserName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void updateByUserName_IllegalArgumentExceptionTest() {
        //given
        Student student = null;
        //when
        studentServiceImpl.updateByUserName(student);
        //then

    }

    @Test(expected = NullPointerException.class)
    public void updateByUserName_NullPointerExceptionTest() {
        //given
        Student student = new Student();
        //when
        studentServiceImpl.updateByUserName(student);
        //then
    }

    @Test
    public void deleteTest() {
        //given
        doNothing().when(studentServiceImpl.studentRepository).delete(student1);
        //when
        studentServiceImpl.studentRepository.delete(student1);
        //then
        verify(studentServiceImpl.studentRepository).delete(student1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_IllegalArgumentExceptionTest() {
        //given
        Student student3 = null;
        //when
        studentServiceImpl.delete(student3);
        //then
    }

    @Test
    public void getAllTest() {
        //given
        when(studentServiceImpl.studentRepository.findAllOrderByLastNameDesc()).thenReturn(studentList);
        //when
        List<Student> all = studentServiceImpl.getAll();
        //
        verify(studentServiceImpl.studentRepository).findAllOrderByLastNameDesc();
        assertEquals(1, all.size());
    }

    @Test
    public void getStudentByIdTest() {
        //given
        long idStudent = student1.getIdStudent();
        when(studentServiceImpl.studentRepository.findOne(idStudent)).thenReturn(student1);
        //when
        studentServiceImpl.getStudentById(idStudent);
        //then
        verify(studentServiceImpl.studentRepository).findOne(idStudent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStudentById_IllegalArgumentExceptionTest() {
        //given
        long idStudent = -1;
        //when
        studentServiceImpl.getStudentById(idStudent);
        //then
    }

    @Test
    public void getStudentByUserNameTest() throws Exception {
        //given
        String userName = student1.getUserName();
        when(studentServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        studentServiceImpl.getStudentByUserName(userName);
        //then
        verify(studentServiceImpl.studentRepository).findByUserName(userName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStudentByUserName_IllegalArgumentExceptionTest() throws Exception {
        //given
        String userName = null;
        //when
        studentServiceImpl.getStudentByUserName(userName);
        //then
    }

    @Test
    public void getClassroomStudentsByClassroomNameDescLastNumberTest() {
        //given
        int classroomNumber = classroom.getClassroomNumber();
        when(studentServiceImpl.studentRepository.findAllOrderByLastNameDesc()).thenReturn(studentList);
        //when
        studentServiceImpl.getClassroomStudentsByClassroomNumberDescLastName(classroomNumber);
        //then
        verify(studentServiceImpl.studentRepository).findByClassroomClassroomNumber(classroomNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getClassroomStudentsByClassroomNameDescLastNumber_IllegalArgumentExceptionTest() {
        //given
        int classroomNumber = 0;
        //when
        studentServiceImpl.getClassroomStudentsByClassroomNumberDescLastName(classroomNumber);
        //then
    }

    @Test
    public void getClassroomStudentsByClassroomNameDescLastNameTest() {
        //given
        String classroomName = classroom.getClassroomName();
        when(studentServiceImpl.studentRepository.findByClassroomClassroomName(classroomName)).thenReturn(studentList);
        //when
        studentServiceImpl.getClassroomStudentsByClassroomNameDescLastName(classroomName);
        //then
        verify(studentServiceImpl.studentRepository).findByClassroomClassroomName(classroomName);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getClassroomStudentsByClassroomNameDescLastName_IllegalArgumentExceptionTest() {
        //given
        String classroomName = null;
        //when
        studentServiceImpl.getClassroomStudentsByClassroomNameDescLastName(classroomName);
        //then

    }

    @Test
    public void getStudentsWithGivenMarkTest() {
        //given
        int markValue = mark.getMarkValue();
        when(studentServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark);
        when(studentServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        //when
        studentServiceImpl.getStudentsWithGivenMarkValue(markValue);
        //then
        verify(studentServiceImpl.studentRepository).findAll();
        verify(studentServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getStudentsWithGivenMark_IllegalArgumentExceptionTest() {
        //given
        int markValue = 10;
        //when
        studentServiceImpl.getStudentsWithGivenMarkValue(markValue);
        //then
    }

    @Test
    public void getStudentsWithoutGivenMarkTest() {
        //given
        Mark mark2 = new Mark();
        mark2.setMarkValue(4);
        int markValue = mark2.getMarkValue();
        when(studentServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark2);
        when(studentServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        //when
        studentServiceImpl.getStudentsWithoutGivenMarkValue(markValue);
        //then
        verify(studentServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);
        verify(studentServiceImpl.studentRepository).findAll();

    }

    @Test(expected = IllegalArgumentException.class)
    public void getStudentsWithoutGivenMark_IllegalArgumentExceptionTest() {
        //given
        int markValue = 10;
        //when
        studentServiceImpl.getStudentsWithoutGivenMarkValue(markValue);
        //then
    }

}