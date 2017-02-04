package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.MarkRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Created by ja on 24.01.17.
 */
public class MarkServiceImplTest {

    private MarkServiceImpl markServiceImpl;
    private Mark mark;
    private List<Mark> markList;


    @Before
    public void setUp() throws Exception {

        markServiceImpl = new MarkServiceImpl();
        markServiceImpl.markRepository = mock(MarkRepository.class);

        mark = new Mark();
        mark.setIdMark(5);
        mark.setMarkValue(5);
        mark.setMarkName("bdb");
        mark.setStudentsWithMark(Collections.emptySet());
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

    @Test(expected = IllegalArgumentException.class)
    public void saveMark_IllegalArgumentExceptionTest() {
        //given
        mark = null;
        //when
        markServiceImpl.saveMark(mark);
        //then
    }

    @Test
    public void updateMarkTest() {
        //given
        when(markServiceImpl.markRepository.findOne(5L)).thenReturn(mark);
        //when
        markServiceImpl.updateMark(5, mark);
        //then
        verify(markServiceImpl.markRepository).findOne(5L);
        verify(markServiceImpl.markRepository).save(mark);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateMark_IllegalArgumentExceptionTest()  {
        //given
        Mark mark3 = new Mark();
        //when
        markServiceImpl.updateMark(0, mark3);
        //then
    }

    @Test(expected = NullPointerException.class)
    public void updateMark_NullPointerExceptionTest()  {
        //given
        Mark mark3 = null;
        //when
        markServiceImpl.updateMark(1L, mark3);
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

    @Test(expected = IllegalArgumentException.class)
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

    @Test(expected = IllegalArgumentException.class)
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

    @Test(expected = IllegalArgumentException.class)
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
        mark.setStudentsWithMark(Collections.emptySet());
        int markValue = mark.getMarkValue();
        when(markServiceImpl.markRepository.findByMarkValueNamedQuery(markValue)).thenReturn(mark);
        //when
        Set<Student> studentsByGivenMarkName = markServiceImpl.getStudentsByGivenMarkValue(markValue);
        //then
        verify(markServiceImpl.markRepository).findByMarkValueNamedQuery(markValue);
        assertEquals(Collections.emptySet(), studentsByGivenMarkName);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetStudentsByGivenMarkValue() {
        //given
        int markValue = 10;
        //when
        markServiceImpl.getStudentsByGivenMarkValue(markValue);
        //then
    }

    @Test(expected = NullPointerException.class)
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
        mark.setStudentsWithMark(Collections.emptySet());
        String markName = mark.getMarkName();
        when(markServiceImpl.markRepository.findByMarkNameNamedQuery(markName)).thenReturn(mark);
        //when
        Set<Student> studentsByGivenMarkName = markServiceImpl.getStudentsByGivenMarkName(markName);
        //then
        verify(markServiceImpl.markRepository).findByMarkNameNamedQuery(markName);
        assertEquals(Collections.emptySet(), studentsByGivenMarkName);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getStudentsByGivenMarkName_IllegalArgumentExceptionTest() {
        //given
        String markName = null;
        //when
        markServiceImpl.getStudentsByGivenMarkName(markName);
        //then
    }

    @Test(expected = NullPointerException.class)
    public void getStudentsByGivenMarkName_NullPointerExceptionTest() {
        //given
        String markName = "brak";
        //when
        markServiceImpl.getStudentsByGivenMarkName(markName);

        //then
    }

}