package com.jakub.ajamarks.services.statisticservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;
import com.jakub.ajamarks.repositories.MarkRepository;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by ja on 24.01.17.
 */
public class StatisticServiceImplTest {

    private StatisticServiceImpl statisticServiceImpl;
    private Student student1;
    private Classroom classroom;
    private Mark mark;
    private List<Student> studentList;
    private ArrayList<Mark> markList;

    @Before
    public void setUp() throws Exception {

        statisticServiceImpl = new StatisticServiceImpl();
        statisticServiceImpl.classroomRepository = mock(ClassroomRepository.class);
        statisticServiceImpl.markRepository = mock(MarkRepository.class);
        statisticServiceImpl.studentRepository = mock(StudentRepository.class);

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
        markList = new ArrayList<>();
        markList.add(mark);
        student1.setMarkList(markList);

        studentList = new ArrayList<>();
        studentList.add(student1);
        Set<Student> studentSet= new TreeSet<>();
        studentSet.add(student1);
        classroom.setStudentsInClassroom(studentSet);


    }

    @Test
    public void countAverageMarkForUserTest() {
        //given
        String userName = student1.getUserName();
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        Double aDouble = statisticServiceImpl.countAverageMarkForUser(userName);
        //then
        verify(statisticServiceImpl.studentRepository).findByUserName(userName);
        assertEquals(new Double(5d), aDouble);
    }

    @Test(expected = IllegalArgumentException.class)
    public void countAverageMarkForUser_IllegalArgumentExceptionTest() {
        //given
        String userName = null;
        //when
        statisticServiceImpl.countAverageMarkForUser(userName);
        //then
    }

    @Test
    public void countAverageMarkForClassroomTest() {
        //given
        String classroomName = classroom.getClassroomName();
        when(statisticServiceImpl.studentRepository.findByClassroomClassroomName(classroomName)).thenReturn(studentList);
        //when
        Double aDouble = statisticServiceImpl.countAverageMarkForClassroom(classroomName);
        //then
        verify(statisticServiceImpl.studentRepository).findByClassroomClassroomName(classroomName);
        assertEquals(new Double(5d), aDouble);
    }

    @Test(expected = IllegalArgumentException.class)
    public void countAverageMarkForClassroom_IllegalArgumentExceptionTest() {
        //given
        String classroomName = null;
        //when
        statisticServiceImpl.countAverageMarkForClassroom(classroomName);
        //then
    }

    @Test
    public void countAverageMarkForSchoolTest() {
        //given
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        //when
        Double aDouble = statisticServiceImpl.countAverageMarkForSchool();
        //then
        verify(statisticServiceImpl.studentRepository).findAll();
        assertEquals(new Double(5d), aDouble);

    }

    @Test
    public void sortStudentsByAverageAscTest() {
        //given
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        //when
        statisticServiceImpl.sortStudentsByAverageAsc();
        //then
        verify(statisticServiceImpl.studentRepository).findAll();
    }

    @Test
    public void isStudentAverageHigherThenInClassroomTest() {
        //given
        String userName = student1.getUserName();
        String classroomName = classroom.getClassroomName();
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        when(statisticServiceImpl.studentRepository.findByClassroomClassroomName(classroomName)).thenReturn(studentList);


        //when
        statisticServiceImpl.isStudentAverageHigherThenInClassroom(userName);
        //then
        verify(statisticServiceImpl.studentRepository, times(2)).findByUserName(userName);
        verify(statisticServiceImpl.studentRepository).findByClassroomClassroomName(classroomName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isStudentAverageHigherThenInClassroom_IllegalArgumentExceptionTest() {
        //given
        String userName = null;
        //when
        statisticServiceImpl.isStudentAverageHigherThenInClassroom(userName);
        //then
    }

    @Test
    public void isStudentAverageHigherThenInSchoolTest() {
        //given
        String userName = student1.getUserName();
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        boolean studentAverageHigherThenInSchool = statisticServiceImpl.isStudentAverageHigherThenInSchool(userName);
        //then
        verify(statisticServiceImpl.studentRepository).findByUserName(userName);
        verify(statisticServiceImpl.studentRepository).findAll();
        assertEquals(false, studentAverageHigherThenInSchool);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isStudentAverageHigherThenInSchool_IllegalArgumentExceptionTest() {
        //given
        String userName = null;
        //when
        statisticServiceImpl.isStudentAverageHigherThenInSchool(userName);
        //then
    }

    @Test
    public void getStudentsAboveSchoolAverageTest() {
        //given
        String userName = student1.getUserName();
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        statisticServiceImpl.getStudentsAboveSchoolAverage();
        //then
        verify(statisticServiceImpl.studentRepository, times(2)).findAll();
        verify(statisticServiceImpl.studentRepository).findByUserName(userName);
    }

    @Test
    public void getStudentsAboveStudentAverageTest() {
        //given
        String userName = student1.getUserName();
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        statisticServiceImpl.getStudentsAboveStudentAverage(userName);
        //then
        verify(statisticServiceImpl.studentRepository).findAll();
        verify(statisticServiceImpl.studentRepository, atLeast(2)).findByUserName(userName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStudentsAboveStudentAverage_IllegalArgumentExceptionTest() {
        //given
        String userName = null;
        //when
        statisticServiceImpl.getStudentsAboveStudentAverage(userName);
        //then
    }

    @Test
    public void countHowManyStudentsAboveSchoolAverageTest() {
        //gicen
        String userName = student1.getUserName();
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        statisticServiceImpl.countHowManyStudentsAboveSchoolAverage();
        //then
        verify(statisticServiceImpl.studentRepository).findAll();
        verify(statisticServiceImpl.studentRepository).findByUserName(userName);
    }

    @Test
    public void countHowManyStudentsAboveStudentAverageTest() {
        //given
        String userName = student1.getUserName();
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        when(statisticServiceImpl.studentRepository.findByUserName(userName)).thenReturn(student1);
        //when
        statisticServiceImpl.countHowManyStudentsAboveStudentAverage(userName);
        //then
        verify(statisticServiceImpl.studentRepository).findAll();
        verify(statisticServiceImpl.studentRepository, atLeast(2)).findByUserName(userName);

    }

    @Test(expected = IllegalArgumentException.class)
    public void countHowManyStudentsAboveStudentAverage_IllegalArgumentExceptionTest() {
        //given
        String userName = null;
        //when
        statisticServiceImpl.countHowManyStudentsAboveStudentAverage(userName);
        //then
        verify(statisticServiceImpl.studentRepository).findByUserName(userName);

    }

    @Test
    public void countQuantityOfEachMarkForSchoolTest() {
        //given
        when(statisticServiceImpl.markRepository.findAll()).thenReturn(markList);
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        //when
        statisticServiceImpl.countQuantityOfEachMarkForSchool();
        //then
        verify(statisticServiceImpl.markRepository).findAll();
        verify(statisticServiceImpl.studentRepository).findAll();
    }

    @Test
    public void getFromClassroomMarkStudentListMapTest() {
        //given
        String classroomName = classroom.getClassroomName();
        when(statisticServiceImpl.markRepository.findAll()).thenReturn(markList);
        when(statisticServiceImpl.classroomRepository.findByClassroomName(classroomName)).thenReturn(classroom);
        //when
        statisticServiceImpl.getFromClassroomMarkStudentListMap(classroomName);
        //then
        verify(statisticServiceImpl.markRepository).findAll();
        verify(statisticServiceImpl.classroomRepository).findByClassroomName(classroomName);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getFromClassroomMarkStudentListMap_IllegalArgumentExceptionTest() {
        //given
        String classroomName = null;
        //when
        statisticServiceImpl.getFromClassroomMarkStudentListMap(classroomName);
        //then
    }

    @Test
    public void getFromSchoolMarkStudentListMapTest() {
        //given
        when(statisticServiceImpl.markRepository.findAll()).thenReturn(markList);
        when(statisticServiceImpl.studentRepository.findAll()).thenReturn(studentList);
        //when
        statisticServiceImpl.getFromSchoolMarkStudentListMap();
        //then
        verify(statisticServiceImpl.markRepository).findAll();
        verify(statisticServiceImpl.studentRepository).findAll();

    }

}