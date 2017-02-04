package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ja on 03.02.17.
 */
public class StudentControllerTest {

    StudentController studentController;
    private Mark mark;
    private List<Mark> markList;
    private Student student;
    private Classroom classroom;
    private List<Classroom> classroomList;
    private Set<Student> studentSetInClassroom;
    private Set<Student> studentSetWithBDBMark;
    private List<Student> studentList;

    @Before
    public void setUp() {
        studentController = new StudentController();
        studentController.studentService = mock(StudentService.class);

        mark = new Mark();
        mark.setIdMark(1);
        mark.setMarkName("BDB");
        mark.setMarkValue(5);

        student = new Student();
        student.setIdStudent(1);
        student.setUserName("JA");
        student.setFirstName("Jak");
        student.setLastName("Ani");

        studentList = new ArrayList<>();
        studentList.add(student);
        studentSetInClassroom = new TreeSet<>();
        studentSetInClassroom.add(student);
        studentSetWithBDBMark = new TreeSet<>();
        studentSetWithBDBMark.add(student);

        classroom = new Classroom();
        classroom.setIdClassroom(1);
        classroom.setClassroomNumber(1);
        classroom.setClassroomName("Pierwsza");
        classroom.setStudentsInClassroom(studentSetInClassroom);

        classroomList = new ArrayList<>();
        classroomList.add(classroom);

        student.setClassroom(classroom);
        mark.setStudentsWithMark(studentSetWithBDBMark);

        markList = new ArrayList<>();
        markList.add(mark);
    }

    @Test
    public void addStudent() throws Exception {
        Student student = new Student();
        student.setUserName("JA");
        student.setFirstName("Jak");
        student.setLastName("Ani");
        when(studentController.studentService.saveStudent(student)).thenReturn(student);
        //when
        ResponseEntity responseEntity = studentController.addStudent("JA", "Jak", "Ani");
        //then
        verify(studentController.studentService).saveStudent(student);
        assertThat(responseEntity.getBody(), is(student));
        assertThat(responseEntity.getStatusCodeValue(), is(201));
    }

    @Test
    public void addStudentThrowException() throws Exception {
        Student student = new Student();
        student.setUserName("JA");
        student.setFirstName("Jak");
        student.setLastName("Ani");
        when(studentController.studentService.saveStudent(student)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.addStudent("JA", "Jak", "Ani");
        //then
        verify(studentController.studentService).saveStudent(student);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void createStudent() {
        //given
        when(studentController.studentService.saveStudent(student)).thenReturn(student);
        //when
        ResponseEntity responseEntity = studentController.createStudent(this.student);
        //then
        verify(studentController.studentService).saveStudent(student);
        assertThat(responseEntity.getBody(), is(student));
        assertThat(responseEntity.getStatusCodeValue(), is(201));
    }

    @Test
    public void createStudentThrowException() {
        //given
        when(studentController.studentService.saveStudent(student)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.createStudent(this.student);
        //then
        verify(studentController.studentService).saveStudent(student);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void updateStudent() {
        //given
        when(studentController.studentService.updateStudent(1, student)).thenReturn(student);
        //when
        ResponseEntity responseEntity = studentController.updateStudent(1, student);
        //then
        verify(studentController.studentService).updateStudent(1, student);
        assertThat(responseEntity.getBody(), is(student));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void updateStudentThrowException() {
        //given
        when(studentController.studentService.updateStudent(1, student)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.updateStudent(1, student);
        //then
        verify(studentController.studentService).updateStudent(1, student);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void deleteStudent() {
        //given
        when(studentController.studentService.getStudentByUserName("JA")).thenReturn(student);
        //when
        ResponseEntity responseEntity = studentController.deleteStudent("JA");
        //then
        verify(studentController.studentService).getStudentByUserName("JA");
        verify(studentController.studentService).delete(student);
        assertThat(responseEntity.getStatusCodeValue(), is(204));
    }

    @Test
    public void deleteStudentThrowException() {
        //given
        when(studentController.studentService.getStudentByUserName("JA")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.deleteStudent("JA");
        //then
        verify(studentController.studentService).getStudentByUserName("JA");
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showAllStudents() {
        //given
        when(studentController.studentService.getAll()).thenReturn(studentList);
        //when
        ResponseEntity responseEntity = studentController.showAllStudents();
        //then
        verify(studentController.studentService).getAll();
        assertThat(responseEntity.getBody(), is(studentList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showAllStudentsThrowException() {
        //given
        when(studentController.studentService.getAll()).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showAllStudents();
        //then
        verify(studentController.studentService).getAll();
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showStudentByUserId() {
        //given
        when(studentController.studentService.getStudentById(1L)).thenReturn(student);
        //when
        ResponseEntity responseEntity = studentController.showStudentByUserId(1L);
        //then
        verify(studentController.studentService).getStudentById(1L);
        assertThat(responseEntity.getBody(), is(student));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showStudentByUserIdThrowException() {
        //given
        when(studentController.studentService.getStudentById(1L)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showStudentByUserId(1L);
        //then
        verify(studentController.studentService).getStudentById(1L);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showStudentByUserName() {
        //given
        when(studentController.studentService.getStudentByUserName("JA")).thenReturn(student);
        //when
        ResponseEntity responseEntity = studentController.showStudentByUserName("JA");
        //then
        verify(studentController.studentService).getStudentByUserName("JA");
        assertThat(responseEntity.getBody(), is(student));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showStudentByUserNameThrowException() {
        //given
        when(studentController.studentService.getStudentByUserName("JA")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showStudentByUserName("JA");
        //then
        verify(studentController.studentService).getStudentByUserName("JA");
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showStudentsWithGivenMarkValue() {
        //given
        when(studentController.studentService.getStudentsWithGivenMarkValue(5)).thenReturn(studentList);
        //when
        ResponseEntity responseEntity = studentController.showStudentsWithGivenMarkValue(5);
        //then
        verify(studentController.studentService).getStudentsWithGivenMarkValue(5);
        assertThat(responseEntity.getBody(), is(studentList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }
    @Test
    public void showStudentsWithGivenMarkValueThrowEsception() {
        //given
        when(studentController.studentService.getStudentsWithGivenMarkValue(5)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showStudentsWithGivenMarkValue(5);
        //then
        verify(studentController.studentService).getStudentsWithGivenMarkValue(5);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showStudentsWithoutGivenMarkValue() {
        //given
        Student student = new Student();
        student.setUserName("nowy");
        student.setFirstName("nowy");
        student.setLastName("nowy");
        student.setStudentMarks(Collections.emptyList());
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentController.studentService.getStudentsWithoutGivenMarkValue(5)).thenReturn(studentList);
        //when
        ResponseEntity responseEntity = studentController.showStudentsWithoutGivenMarkValue(5);
        //then
        verify(studentController.studentService).getStudentsWithoutGivenMarkValue(5);
        assertThat(responseEntity.getBody(), is(studentList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showStudentsWithoutGivenMarkValueThrowException() {
        //given
        when(studentController.studentService.getStudentsWithoutGivenMarkValue(5)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showStudentsWithoutGivenMarkValue(5);
        //then
        verify(studentController.studentService).getStudentsWithoutGivenMarkValue(5);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showStudentsByClassroomNumber() {
        //given
        when(studentController.studentService.getClassroomStudentsByClassroomNumberDescLastName(1)).thenReturn(studentList);
        //when
        ResponseEntity responseEntity = studentController.showStudentsByClassroomNumber(1);
        //then
        verify(studentController.studentService).getClassroomStudentsByClassroomNumberDescLastName(1);
        assertThat(responseEntity.getBody(), is(studentList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showStudentsByClassroomNumberThrowException() {
        //given
        when(studentController.studentService.getClassroomStudentsByClassroomNumberDescLastName(1)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showStudentsByClassroomNumber(1);
        //then
        verify(studentController.studentService).getClassroomStudentsByClassroomNumberDescLastName(1);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showStudentsByClassroomName() {
        //given
        when(studentController.studentService.getClassroomStudentsByClassroomNameDescLastName("Pierwsza")).thenReturn(studentList);
        //when
        ResponseEntity responseEntity = studentController.showStudentsByClassroomName("Pierwsza");
        //then
        verify(studentController.studentService).getClassroomStudentsByClassroomNameDescLastName("Pierwsza");
        assertThat(responseEntity.getBody(), is(studentList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showStudentsByClassroomNameThrowException() {
        //given
        when(studentController.studentService.getClassroomStudentsByClassroomNameDescLastName("Pierwsza")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = studentController.showStudentsByClassroomName("Pierwsza");
        //then
        verify(studentController.studentService).getClassroomStudentsByClassroomNameDescLastName("Pierwsza");
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

}