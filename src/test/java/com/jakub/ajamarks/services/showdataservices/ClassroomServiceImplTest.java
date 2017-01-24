package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.*;


/**
 * Created by ja on 23.01.17.
 */
public class ClassroomServiceImplTest {

    private ClassroomServiceImpl classroomServiceImpl;
    private Classroom classroom1;
    private Classroom classroom2;
    private List<Classroom> classroomList;
    private Set<Student> studentSet;

    @BeforeMethod
    public void testSetUp(){

        Student student1 = new Student();
        student1.setFirstName("Jakub");
        student1.setLastName("Jakub2");
        student1.setUserName("Jakub3");

        Student student2 = new Student();
        student2.setFirstName("Tomasz");
        student2.setLastName("Tomasz2");
        student2.setUserName("Tomasz3");
        studentSet = new TreeSet<>();
        Collections.addAll(studentSet, student1, student2);

        classroom1 = new Classroom();
        classroom1.setIdClassroom(1);
        classroom1.setClassroomNumber(1);
        classroom1.setClassroomName("Pierwsza");
        classroom1.setStudentsInClassroom(Collections.emptySet());

        classroom2 = new Classroom();
        classroom2.setIdClassroom(2);
        classroom2.setClassroomNumber(2);
        classroom2.setClassroomName("Druga");
        classroom2.setStudentsInClassroom(studentSet);
        classroomList = new ArrayList<>();
        Collections.addAll(classroomList, classroom1, classroom2);

        classroomServiceImpl = new ClassroomServiceImpl();
        classroomServiceImpl.classroomRepository = mock(ClassroomRepository.class);
    }

    @Test
    public void saveClassroomTest(){
        //given
        when(classroomServiceImpl.classroomRepository.save(classroom1)).thenReturn(classroom1);
        //when
        classroomServiceImpl.saveClassroom(classroom1);
        //then
        verify(classroomServiceImpl.classroomRepository).save(classroom1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void saveClassroom_IllegalArgumentExceptionTest(){
        //given
        Classroom classroom3 = null;
        //when
        classroomServiceImpl.saveClassroom(classroom3);
        //then
    }

    @Test
    public void deleteTest(){
        //given
        doNothing().when(classroomServiceImpl.classroomRepository).delete(classroom1);
        //when
        classroomServiceImpl.delete(classroom1);
        //then
        verify(classroomServiceImpl.classroomRepository).delete(classroom1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void delete_IllegalArgumentExceptionTest(){
        //given
        Classroom classroom3 = null;
        //when
        classroomServiceImpl.delete(classroom3);
        //then
    }

    @Test
    public void updateTest(){
        //given
        when(classroomServiceImpl.classroomRepository.findByClassroomName(classroom1.getClassroomName())).thenReturn(classroom1);
        //when
        classroomServiceImpl.updateClassroom(classroom1);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomName(classroom1.getClassroomName());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void update_IllegalArgumentExceptionTest(){
        //given
        Classroom classroom3 = new Classroom();
        //when
        classroomServiceImpl.updateClassroom(classroom3);
        //then
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void update_NullPointerExceptionTest(){
        //given
        Classroom classroom3 = null;
        //when
        classroomServiceImpl.updateClassroom(classroom3);
        //then
    }

    @Test
    public void getAllByClassroomNameAscTest(){
        //given
        when(classroomServiceImpl.classroomRepository.findAllByOrderByClassroomNameAsc()).thenReturn(classroomList);
        //when
        List<Classroom> allByClassroomNameAsc = classroomServiceImpl.getAllByClassroomNameAsc();
        //then
        verify(classroomServiceImpl.classroomRepository).findAllByOrderByClassroomNameAsc();
    }

    @Test
    public void getClassroomByIdTest(){
        //given
        long idClassroom = classroom1.getIdClassroom();
        when(classroomServiceImpl.classroomRepository.findOne(idClassroom)).thenReturn(classroom1);
        //when
        classroomServiceImpl.getClassroomById(idClassroom);
        //then
        verify(classroomServiceImpl.classroomRepository).findOne(idClassroom);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getClassroomById_IllegalArgumentExceptionTest(){
        //given
        long idClassroom = 0;
        //when
        classroomServiceImpl.getClassroomById(idClassroom);
        //then
    }

    @Test
    public void getClassroomByNumberTest(){
        //given
        int classroomNumber = classroom1.getClassroomNumber();
        when(classroomServiceImpl.classroomRepository.findByClassroomNumber(classroomNumber)).thenReturn(classroom1);
        //when
        classroomServiceImpl.getClassroomByNumber(classroomNumber);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomNumber(classroomNumber);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getClassroomByNumber_IllegalArgumentExceptionTest(){
        //given
        int classroomNumber = 0;
        //when
        classroomServiceImpl.getClassroomByNumber(classroomNumber);
        //then
    }

    @Test
    public void getClassroomByNameTest(){
        //given
        String classroomName = classroom1.getClassroomName();
        when(classroomServiceImpl.classroomRepository.findByClassroomName(classroomName)).thenReturn(classroom1);
        //when
        classroomServiceImpl.getClassroomByName(classroomName);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomName(classroomName);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getClassroomByName_IllegalArgumentExceptionTest(){
        //given
        String classroomName = null;
        //when
        classroomServiceImpl.getClassroomByName(classroomName);
        //then
    }

    @Test
    public void getClassroomStudentsByClassroomNumberTest(){
        //given
        int classroomNumber = classroom2.getClassroomNumber();
        when(classroomServiceImpl.classroomRepository.findByClassroomNumber(classroomNumber)).thenReturn(classroom2);
        //when
        classroomServiceImpl.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomNumber(classroomNumber);

    }

    @Test
    public void getClassroomStudentsByClassroomNumber_NoStudentsTest(){
        //given
        int classroomNumber = classroom1.getClassroomNumber();
        when(classroomServiceImpl.classroomRepository.findByClassroomNumber(classroomNumber)).thenReturn(classroom1);
        //when
        Set<Student> classroomStudentsByClassroomNumber = classroomServiceImpl.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomNumber(classroomNumber);
        assertEquals(Collections.emptySet(), classroomStudentsByClassroomNumber);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getClassroomStudentsByClassroomNumber_IllegalArgumentExceptionTest(){
        //given
        int classroomNumber = 0;
        //when
        classroomServiceImpl.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getClassroomStudentsByClassroomNumber_NullPointerExceptionTest(){
        //given
        int classroomNumber = 21;
        //when
        classroomServiceImpl.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then
    }


    @Test
    public void getClassroomStudentsByClassroomNameTest(){
        //given
        String classroomName = classroom2.getClassroomName();
        when(classroomServiceImpl.classroomRepository.findByClassroomName(classroomName)).thenReturn(classroom2);
        //when
        classroomServiceImpl.getClassroomStudentsByClassroomName(classroomName);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomName(classroomName);

    }

    @Test
    public void getClassroomStudentsByClassroomName_NoStudentsTest(){
        //given
        String classroomName = classroom1.getClassroomName();
        when(classroomServiceImpl.classroomRepository.findByClassroomName(classroomName)).thenReturn(classroom1);
        //when

        Set<Student> classroomStudentsByClassroomName = classroomServiceImpl.getClassroomStudentsByClassroomName(classroomName);
        //then
        verify(classroomServiceImpl.classroomRepository).findByClassroomName(classroomName);
        assertEquals(Collections.emptySet(), classroomStudentsByClassroomName);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getClassroomStudentsByClassroomName_IllegalArgumentExceptionTest(){
        //given
        String classroomName = null;
        //when
        classroomServiceImpl.getClassroomStudentsByClassroomName(classroomName);
        //then

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getClassroomStudentsByClassroomName_NullPointerExceptionTest(){
        //given
        String classroomName = "no name";
        //when
        classroomServiceImpl.getClassroomStudentsByClassroomName(classroomName);
        //then

    }


}
