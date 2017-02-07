package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.ClassroomService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ja on 03.02.17.
 */
public class ClassroomControllerTest {

    ClassroomController classroomController;
    private Mark mark;
    private List<Mark> markList;
    private Student student;
    private Classroom classroom;
    private List<Classroom> classroomList;
    private Set<Student> studentSetInClassroom;
    private Set<Student> studentSetWithBDBMark;

    @Before
    public void setUp() {

        classroomController = new ClassroomController();
        classroomController.classroomServiceImpl = mock(ClassroomService.class);

        mark = new Mark();
        mark.setIdMark(1);
        mark.setMarkName("BDB");
        mark.setMarkValue(5);

        student = new Student();
        student.setIdStudent(1);
        student.setUserName("JA");
        student.setFirstName("Jak");
        student.setLastName("Ani");

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
    public void addClassroom() {
        //given
        Classroom classroom = new Classroom();
        classroom.setClassroomName("Pierwsza");
        classroom.setClassroomNumber(1);
        //when
        ResponseEntity responseEntity = classroomController.addClassroom(1, "Pierwsza");
        //then
        verify(classroomController.classroomServiceImpl).saveClassroom(classroom);
        assertThat(responseEntity.getStatusCodeValue(), is(201));
    }

    @Test
    public void addClassroomThrowException() {
        //given
        Classroom classroom = new Classroom();
        classroom.setClassroomName("Pierwsza");
        classroom.setClassroomNumber(1);
        when(classroomController.classroomServiceImpl.saveClassroom(classroom)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.addClassroom(1, "Pierwsza");
        //then
        verify(classroomController.classroomServiceImpl).saveClassroom(classroom);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void createClassroom() {
        //given
        when(classroomController.classroomServiceImpl.saveClassroom(classroom)).thenReturn(classroom);
        //when
        ResponseEntity responseEntity = classroomController.createClassroom(classroom);
        //then
        verify(classroomController.classroomServiceImpl).saveClassroom(classroom);
        assertThat(responseEntity.getStatusCodeValue(), is(201));
        assertThat(responseEntity.getBody(), is(classroom));
    }

    @Test
    public void createClassroomThrowException() {
        //given
        when(classroomController.classroomServiceImpl.saveClassroom(classroom)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.createClassroom(this.classroom);
        //then
        verify(classroomController.classroomServiceImpl).saveClassroom(classroom);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void updateClassroom() {
        //given
        when(classroomController.classroomServiceImpl.updateClassroom(1, classroom)).thenReturn(classroom);
        //when
        ResponseEntity responseEntity = classroomController.updateClassroom(1, classroom);
        //then
        verify(classroomController.classroomServiceImpl).updateClassroom(1, classroom);
        assertThat(responseEntity.getBody(), is(classroom));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void updateClassroomThrowException() {
        //given
        when(classroomController.classroomServiceImpl.updateClassroom(1, classroom)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.updateClassroom(1, classroom);
        //then
        verify(classroomController.classroomServiceImpl).updateClassroom(1, classroom);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void deleteClassroom() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomByNumber(1)).thenReturn(classroom);
        //when
        ResponseEntity responseEntity = classroomController.deleteClassroom(1);
        //then
        verify(classroomController.classroomServiceImpl).delete(classroom);
        assertThat(responseEntity.getStatusCodeValue(), is(204));
    }

    @Test
    public void deleteClassroomThrowException() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomByNumber(1)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.deleteClassroom(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomByNumber(1);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showAllClassrooms() {
        //given
        when(classroomController.classroomServiceImpl.getAllByClassroomNameAsc()).thenReturn(classroomList);
        //when
        ResponseEntity responseEntity = classroomController.showAllClassrooms();
        //then
        verify(classroomController.classroomServiceImpl).getAllByClassroomNameAsc();
        assertThat(responseEntity.getBody(), is(classroomList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showAllClassroomsThrowException() {
        //given
        when(classroomController.classroomServiceImpl.getAllByClassroomNameAsc()).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.showAllClassrooms();
        //then
        verify(classroomController.classroomServiceImpl).getAllByClassroomNameAsc();
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showClassroomById() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomById(1)).thenReturn(classroom);
        //when
        ResponseEntity responseEntity = classroomController.showClassroomById(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomById(1);
        assertThat(responseEntity.getBody(), is(classroom));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showClassroomByIdThrowException() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomById(1)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.showClassroomById(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomById(1);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showClassroomByNumber()  {
        //given
        when(classroomController.classroomServiceImpl.getClassroomByNumber(1)).thenReturn(classroom);
        //when
        ResponseEntity responseEntity = classroomController.showClassroomByNumber(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomByNumber(1);
        assertThat(responseEntity.getBody(), is(classroom));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showClassroomByNumberThrowExceptin()  {
        //given
        when(classroomController.classroomServiceImpl.getClassroomByNumber(1)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.showClassroomByNumber(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomByNumber(1);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showClassroomByName() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomByName("Pierwsza")).thenReturn(classroom);
        //when
        ResponseEntity responseEntity = classroomController.showClassroomByName("Pierwsza");
        //then
        verify(classroomController.classroomServiceImpl).getClassroomByName("Pierwsza");
        assertThat(responseEntity.getBody(), is(classroom));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showClassroomByNameThrowException() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomByName("Pierwsza")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.showClassroomByName("Pierwsza");
        //then
        verify(classroomController.classroomServiceImpl).getClassroomByName("Pierwsza");
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showClassroomStudentsByClassroomNumber() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomStudentsByClassroomNumber(1)).thenReturn(studentSetInClassroom);
        //when
        ResponseEntity responseEntity = classroomController.showClassroomStudentsByClassroomNumber(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomStudentsByClassroomNumber(1);
        assertThat(responseEntity.getBody(), is(new ArrayList<>(studentSetInClassroom)));
        assertThat(responseEntity.getStatusCodeValue(),is(200));
    }

    @Test
    public void showClassroomStudentsByClassroomNumberThrowException() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomStudentsByClassroomNumber(1)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.showClassroomStudentsByClassroomNumber(1);
        //then
        verify(classroomController.classroomServiceImpl).getClassroomStudentsByClassroomNumber(1);
        assertThat(responseEntity.getStatusCodeValue(),is(404));
    }


    @Test
    public void showClassroomStudentsByClassroomName() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomStudentsByClassroomName("Pierwsza")).thenReturn(studentSetInClassroom);
        //when
        ResponseEntity responseEntity = classroomController.showClassroomStudentsByClassroomName("Pierwsza");
        //then
        verify(classroomController.classroomServiceImpl).getClassroomStudentsByClassroomName("Pierwsza");
        assertThat(responseEntity.getBody(), is(new ArrayList<>(studentSetInClassroom)));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showClassroomStudentsByClassroomNameThrowexception() {
        //given
        when(classroomController.classroomServiceImpl.getClassroomStudentsByClassroomName("Pierwsza")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = classroomController.showClassroomStudentsByClassroomName("Pierwsza");
        //then
        verify(classroomController.classroomServiceImpl).getClassroomStudentsByClassroomName("Pierwsza");
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

}