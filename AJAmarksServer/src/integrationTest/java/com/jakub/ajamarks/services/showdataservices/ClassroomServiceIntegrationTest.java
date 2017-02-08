package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.config.ForTestConfiguration;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ForTestConfiguration.class})
@Transactional
public class ClassroomServiceIntegrationTest {

    @Autowired
    ClassroomService classroomService;
    @Autowired
    StudentService studentService;

    private Classroom classroom1, classroom2;
    private Classroom saveClassroom;
    private Student savedStudent1;
    private Set<Student> studentList;

    @Before
    public void Setup() {

        classroom1 = new Classroom();

        savedStudent1 = new Student();
        savedStudent1.setFirstName("Jakub");
        savedStudent1.setLastName("An");
        savedStudent1.setUserName("aja");
        savedStudent1.setClassroom(classroom1);
        savedStudent1.setStudentMarks(Collections.emptyList());
        studentList = new HashSet<>();
        studentList.add(savedStudent1);

        classroom1.setClassroomNumber(1);
        classroom1.setClassroomName("Pierwsza");
        classroom1.setStudentsInClassroom(studentList);

        saveClassroom = classroomService.saveClassroom(classroom1);
        studentService.saveStudent(savedStudent1);
    }

    @Test
    public void saveClassroomTest() {
        //given
        classroom2 = new Classroom();
        classroom2.setClassroomNumber(2);
        classroom2.setClassroomName("Druga");
        classroom2.setStudentsInClassroom(studentList);
        //when
        Classroom saveClassroom2 = classroomService.saveClassroom(classroom2);
        //then
        assertThat(3, is(classroomService.getAllByClassroomNameAsc().size()));
        assertThat(classroom2, is(saveClassroom2));
    }

    @Test
    public void updateClassroomTest() {
        //given
        Classroom cls = new Classroom();
        cls.setClassroomNumber(11);
        cls.setClassroomName("Jedenasta");
        cls.setStudentsInClassroom(Collections.emptySet());
        Classroom pierwsza = classroomService.getClassroomByName("Pierwsza");
        long id = pierwsza.getIdClassroom();
        //when
        Classroom update = classroomService.updateClassroom(id, cls);
        //then
        assertThat(cls.getClassroomNumber(), is(update.getClassroomNumber()));
        assertThat(11, is(update.getClassroomNumber()));
        assertThat("Jedenasta", is(update.getClassroomName()));
    }

    @Test
    public void deleteTest() {
        //given
        //when
        Classroom classroom = new Classroom();
        classroom.setClassroomNumber(12);
        classroom.setClassroomName("Dwanascie");
        classroomService.saveClassroom(classroom);
        assertThat(3, is(classroomService.getAllByClassroomNameAsc().size()));
        classroomService.delete(classroom);
        //then
        assertThat(2, is(classroomService.getAllByClassroomNameAsc().size()));
    }

    @Test
    public void getAllByClassroomNameAscTest() {
        //given
        //when
        List<Classroom> classroomList = classroomService.getAllByClassroomNameAsc();
        //then
        assertThat(2, is(classroomList.size()));
        assertThat(classroomList, hasItem(classroom1));
    }

    @Test
    public void getClassroomByIdTest() {
        //given
        long idClassroom = saveClassroom.getIdClassroom();
        //when
        Classroom one = classroomService.getClassroomById(idClassroom);
        //then
        assertThat(classroom1, is(one));
    }

    @Test
    public void getClassroomByName() {
        //given
        String classroomName = saveClassroom.getClassroomName();
        //when
        Classroom one = classroomService.getClassroomByName(classroomName);
        //then
        assertThat(saveClassroom, is(one));
    }

    @Test
    public void getClassroomByNumberTest() {
        //given
        int classroomNumber = saveClassroom.getClassroomNumber();
        //when
        classroomService.getClassroomByNumber(classroomNumber);
        //then
        assertThat(1, is(classroomNumber));
    }

    @Test
    public void getClassroomStudentsByClassroomNumberTest() {
        //given
        int classroomNumber = saveClassroom.getClassroomNumber();
        //when
        Collection<Student> classroomStudentsByClassroomNumber = classroomService.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then
        assertThat(classroomStudentsByClassroomNumber, hasItem(savedStudent1));
        assertSame(classroomStudentsByClassroomNumber.toArray()[0], savedStudent1);
    }

    @Test(expected = NullPointerException.class)
    public void getClassroomStudentsByClassroomNumber_NoGivenClassroomNumberTest() {
        //given
        int classroomNumber = 111;
        Classroom classroomById = classroomService.getClassroomById(101);
        classroomService.delete(classroomById);
        //when
        classroomService.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then

    }

    @Test
    public void getClassroomStudentsByClassroomNumber_NoStudentsForGivenClassroomNumberTest() {
        //given
        int classroomNumber = saveClassroom.getClassroomNumber();
        saveClassroom.setStudentsInClassroom(Collections.emptySet());
        //when
        Set<Student> classroomStudentsByClassroomNumber = classroomService.getClassroomStudentsByClassroomNumber(classroomNumber);
        //then
        assertThat(0, is(classroomStudentsByClassroomNumber.size()));
    }


    @Test
    public void getClassroomStudentsByClassroomNameTest() {
        //given
        String classroomName = saveClassroom.getClassroomName();

        //when
        Collection<Student> classroomStudentsByClassroomName = classroomService.getClassroomStudentsByClassroomName(classroomName);
        //then
        assertThat(classroomStudentsByClassroomName, hasItem(savedStudent1));
        assertSame(classroomStudentsByClassroomName.toArray()[0], savedStudent1);
    }

    @Test(expected = NullPointerException.class)
    public void getClassroomStudentsByClassroomName_NoGivenClassroomNameTest() {
        //given
        String classroomName = "no name";
        //when
        classroomService.getClassroomStudentsByClassroomName(classroomName);
        //then
    }

    @Test
    public void getClassroomStudentsByClassroomName_NoStudentsForGivenClassroomNameTest() {
        //given
        String classroomName = saveClassroom.getClassroomName();
        saveClassroom.setStudentsInClassroom(Collections.emptySet());
        //when
        Set<Student> classroomStudentsByClassroomName = classroomService.getClassroomStudentsByClassroomName(classroomName);
        //then
        assertThat(0, is(classroomStudentsByClassroomName.size()));
    }


}
