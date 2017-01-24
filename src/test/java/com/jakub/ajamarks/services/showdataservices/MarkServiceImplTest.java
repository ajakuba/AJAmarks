package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.MarkRepository;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by ja on 24.01.17.
 */
public class MarkServiceImplTest {

    private MarkServiceImpl markServiceImpl;
    private Mark mark;
    private List<Mark> markList;


    @BeforeMethod
    public void setUp() throws Exception {

        markServiceImpl = new MarkServiceImpl();
        markServiceImpl.markRepository = mock(MarkRepository.class);

        mark = new Mark();
        mark.setIdMark(5);
        mark.setMarkValue(5);
        mark.setMarkName("bdb");
        mark.setStudentSet(Collections.emptySet());
        markList = new ArrayList<>();
        markList.add(mark);

    }

    @Test
    public void saveMarkTest() {
        //given
        when(markServiceImpl.markRepository.save(mark)).thenReturn(mark);
        //when
        markServiceImpl.saveMark(mark);
        //then
        verify(markServiceImpl.markRepository).save(mark);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void saveMark_IllegalArgumentExceptionTest() {
        //given
        mark = null;
        //when
        markServiceImpl.saveMark(mark);
        //then
    }

    @Test
    public void updateByMarkValueTest() {
        //given
        int markValue = mark.getMarkValue();
        when(markServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark);
        //when
        markServiceImpl.updateByMarkValue(mark);
        //then
        verify(markServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateByMarkValue_IllegalArgumentExceptionTest()  {
        //given
        Mark mark3 = null;

        //when
        markServiceImpl.updateByMarkValue(mark3);
        //then
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateByMarkValue_NullPointerExceptionTest()  {
        //given
        Mark mark3 = new Mark();

        //when
        markServiceImpl.updateByMarkValue(mark3);
        //then
    }

    @Test
    public void deleteTest() {
        //given
        doNothing().when(markServiceImpl.markRepository).delete(mark);
        //when
        markServiceImpl.delete(mark);
        //then
        verify(markServiceImpl.markRepository).delete(mark);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteTest_IllegalArgumentException() {
        //given
        Mark mark3 = null;
        //when
        markServiceImpl.delete(mark3);
        //then
    }

    @Test
    public void getAllTest() {
        //given
        when(markServiceImpl.markRepository.findAll()).thenReturn(markList);
        //when
        List<Mark> all = markServiceImpl.getAll();
        //then
        verify(markServiceImpl.markRepository).findAll();
        assertEquals(1, all.size());
    }

    @Test
    public void getByMarkValueTest() {
        //given
        int markValue = mark.getMarkValue();
        when(markServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark);
        //when
        markServiceImpl.getByMarkValue(markValue);
        //
        verify(markServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByMarkValue_IllegalArgumentExceptionTest() {
        //given
        int markValue = 7;
        //when
        markServiceImpl.getByMarkValue(markValue);
        //then
    }

    @Test
    public void getByMarkName() {
        //given
        String markName = mark.getMarkName();
        when(markServiceImpl.markRepository.findByMarkNameNamedQuery(markName)).thenReturn(mark);
        //when
        markServiceImpl.getByMarkName(markName);
        //then
        verify(markServiceImpl.markRepository).findByMarkNameNamedQuery(markName);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByMarkName_IllegalArgumentExceptionTest() {
        //given
        String markName = null;
        //when
        markServiceImpl.getByMarkName(markName);
        //then
    }

    @Test
    public void getStudentsByGivenMarkValueTest() {
        //given
        int markValue = mark.getMarkValue();
        when(markServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark);
        //when
        markServiceImpl.getStudentsByGivenMarkValue(markValue);
        //then
        verify(markServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);
    }

    @Test
    public void getStudentsByGivenMarkValue_NoStudentsTest() {
        //given
        mark.setStudentSet(Collections.emptySet());
        int markValue = mark.getMarkValue();
        when(markServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark);
        //when
        Set<Student> studentsByGivenMarkName = markServiceImpl.getStudentsByGivenMarkValue(markValue);
        //then
        verify(markServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);
        assertEquals(Collections.emptySet(), studentsByGivenMarkName);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetStudentsByGivenMarkValue() {
        //given
        int markValue = 10;
        //when
        markServiceImpl.getStudentsByGivenMarkValue(markValue);
        //then
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getStudentsByGivenMarkValue_NullPointerExceptionTest() {
        //given
        int markValue = 3;
        //when
        markServiceImpl.getStudentsByGivenMarkValue(markValue);
        //then
    }


    @Test
    public void getStudentsByGivenMarkNameTest() {
        //given
        String markName = mark.getMarkName();
        when(markServiceImpl.markRepository.findByMarkNameNamedQuery(markName)).thenReturn(mark);
        //when
        markServiceImpl.getStudentsByGivenMarkName(markName);
        //then
        verify(markServiceImpl.markRepository).findByMarkNameNamedQuery(markName);
    }

    @Test
    public void getStudentsByGivenMarkName_NoStudentsTest() {
        //given
        mark.setStudentSet(Collections.emptySet());
        String markName = mark.getMarkName();
        when(markServiceImpl.markRepository.findByMarkNameNamedQuery(markName)).thenReturn(mark);
        //when
        Set<Student> studentsByGivenMarkName = markServiceImpl.getStudentsByGivenMarkName(markName);
        //then
        verify(markServiceImpl.markRepository).findByMarkNameNamedQuery(markName);
        assertEquals(Collections.emptySet(), studentsByGivenMarkName);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getStudentsByGivenMarkName_IllegalArgumentExceptionTest() {
        //given
        String markName = null;
        //when
        markServiceImpl.getStudentsByGivenMarkName(markName);
        //then
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getStudentsByGivenMarkName_NullPointerExceptionTest() {
        //given
        String markName = "brak";
        //when
        markServiceImpl.getStudentsByGivenMarkName(markName);

        //then
    }

}