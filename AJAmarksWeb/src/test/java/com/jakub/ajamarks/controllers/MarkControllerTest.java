package com.jakub.ajamarks.controllers;


import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.MarkService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ja on 03.02.17.
 */
public class MarkControllerTest {


    private MarkController markController;
    private Mark mark;
    private List<Mark> markList;
    private Student student;
    private Classroom classroom;
    private Set<Student> studentSetInClassroom;
    private Set<Student> studentSetWithBDBMark;


    @Before
    public void setUp() throws Exception {

        markController = new MarkController();
        markController.markService = mock(MarkService.class);
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

        student.setClassroom(classroom);
        mark.setStudentsWithMark(studentSetWithBDBMark);

        markList = new ArrayList<>();
        markList.add(mark);

    }

    @Test
    public void addMark() {
        //given
        //when
        ResponseEntity responseEntity = markController.addMark("BDB", 5);
        //then
        verify(markController.markService).saveMark(this.mark);
        assertThat(responseEntity.getStatusCodeValue(), is(201));
    }

    @Test
    public void addMarkThrowException() {
        //given
        when(markController.markService.saveMark(mark)).thenThrow(new RuntimeException());
        //when
        ResponseEntity addedMark = markController.addMark("BDB", 5);
        //then
        verify(markController.markService).saveMark(this.mark);
        assertThat(addedMark.getStatusCodeValue(), is(406));
    }

    @Test
    public void createMark() {
        //given
        when(markController.markService.saveMark(mark)).thenReturn(mark);
        //when
        ResponseEntity createdMark = markController.createMark(mark);
        //then
        verify(markController.markService).saveMark(mark);
        assertThat(createdMark.getBody(), is(mark));
        assertThat(createdMark.getStatusCodeValue(), is(201));
    }

    @Test
    public void createMarkThrowException() {
        //given
        when(markController.markService.saveMark(mark)).thenThrow(new RuntimeException());
        //when
        ResponseEntity createdMark = markController.createMark(mark);
        //then
        verify(markController.markService).saveMark(mark);
        assertEquals(createdMark.getStatusCodeValue(), 406);
    }

    @Test
    public void deleteMark() {
        //given
        when(markController.markService.getByMarkValue(5)).thenReturn(mark);
        //when
        ResponseEntity responseEntity = markController.deleteMark(5);
        //then
        verify(markController.markService).delete(mark);
        assertThat(responseEntity.getStatusCodeValue(), is(204));
    }

    @Test
    public void deleteMarkThrowException() {
        //given
        when(markController.markService.getByMarkValue(5)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.deleteMark(5);
        //then
        verify(markController.markService).getByMarkValue(5);
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void updateMark() {
        //given
        when(markController.markService.updateMark(1, mark)).thenReturn(mark);
        //when
        ResponseEntity responseEntity = markController.updateMark(1, mark);
        //then
        verify(markController.markService).updateMark(1, mark);
        assertThat(responseEntity.getBody(), is(mark));
    }

    @Test
    public void updateMarkThrowException() {
        //given
        when(markController.markService.updateMark(1, mark)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.updateMark(1, mark);
        //then
        verify(markController.markService).updateMark(1, mark);
        assertThat(responseEntity.getStatusCodeValue(), is(406));
    }

    @Test
    public void showAllMarks() {
        //given
        when(markController.markService.getAll()).thenReturn(markList);
        //when
        ResponseEntity responseEntity = markController.showAllMarks();
        //then
        verify(markController.markService).getAll();
        assertThat(responseEntity.getBody(), is(markList));
        assertThat(responseEntity.getStatusCodeValue(), is(200));

    }

    @Test
    public void showAllMarksThrowException() {
        //given
        when(markController.markService.getAll()).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.showAllMarks();
        //then
        verify(markController.markService).getAll();
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void showByMarkValue() {
        //given
        when(markController.markService.getByMarkValue(5)).thenReturn(mark);
        //when
        ResponseEntity responseEntity = markController.showByMarkValue(5);
        //then
        verify(markController.markService).getByMarkValue(5);
        assertThat(responseEntity.getBody(), is(mark));

    }

    @Test
    public void showByMarkValueLes1() {
        //given
       //when
        ResponseEntity responseEntity = markController.showByMarkValue(0);
        //then
        assertThat(responseEntity.getStatusCodeValue(), is(404));

    }

    @Test(expected = RuntimeException.class)
    public void showByMarkValueThrowException() {
        //given
        when(markController.markService.getByMarkValue(5)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.showByMarkValue(5);
        //then
        verify(markController.markService).getByMarkValue(5);
        assertThat(responseEntity.getStatusCodeValue(), is(404));

    }

    @Test
    public void showByMarkName() {
        //given
        when(markController.markService.getByMarkName("BDB")).thenReturn(mark);
        //when
        ResponseEntity responseEntity = markController.showByMarkName("BDB");
        //then
        verify(markController.markService).getByMarkName("BDB");
        assertThat(responseEntity.getBody(), is(mark));
        assertThat(responseEntity.getStatusCodeValue(), is(200));

    }

    @Test
    public void showByMarkNameThroWxceptio() {
        //given
        when(markController.markService.getByMarkName("BDB")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.showByMarkName("BDB");
        //then
        verify(markController.markService).getByMarkName("BDB");
        assertThat(responseEntity.getStatusCodeValue(), is(404));

    }

    @Test
    public void showStudentsByGivenMarkValue() {
        //given
        when(markController.markService.getStudentsByGivenMarkValue(5)).thenReturn(studentSetWithBDBMark);

        //when
        ResponseEntity responseEntity = markController.showStudentsByGivenMarkValue(5);
        //then
        verify(markController.markService).getStudentsByGivenMarkValue(5);
        assertThat(responseEntity.getBody(), is(new ArrayList<>(studentSetWithBDBMark)));
        assertThat(responseEntity.getStatusCodeValue(), is(200));

    }

    @Test
    public void showStudentsByGivenMarkValueThrowException() {
        //given
        when(markController.markService.getStudentsByGivenMarkValue(5)).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.showStudentsByGivenMarkValue(5);
        //then
        verify(markController.markService).getStudentsByGivenMarkValue(5);
        assertThat(responseEntity.getStatusCodeValue(), is(404));

    }

    @Test
    public void showStudentsByGivenMarkName() {
        //given
        when(markController.markService.getStudentsByGivenMarkName("BDB")).thenReturn(studentSetWithBDBMark);
        //when
        ResponseEntity responseEntity = markController.showStudentsByGivenMarkName("BDB");
        //then
        verify(markController.markService).getStudentsByGivenMarkName("BDB");
        assertThat(responseEntity.getBody(), is(new ArrayList<>(studentSetWithBDBMark)));
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

    @Test
    public void showStudentsByGivenMarkNameThrowException() {
        //given
        when(markController.markService.getStudentsByGivenMarkName("BDB")).thenThrow(new RuntimeException());
        //when
        ResponseEntity responseEntity = markController.showStudentsByGivenMarkName("BDB");
        //then
        verify(markController.markService).getStudentsByGivenMarkName("BDB");
        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }



}