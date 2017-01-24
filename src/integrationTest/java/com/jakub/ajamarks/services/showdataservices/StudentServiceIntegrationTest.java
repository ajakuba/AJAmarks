package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.config.DataBaseForTestConfiguration;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ja on 16.01.17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseForTestConfiguration.class)
@Transactional
public class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private MarkService markService;

    private Student student1, student2;
    private Classroom classroom;
    private Mark mark1, mark2;
    private List<Mark> markList1, markList2;


    @Before
    public void setup() {

        classroom = new Classroom();

        student1 = new Student();
        student1.setFirstName("Jakub");
        student1.setLastName("Ja");
        student1.setUserName("aja");
        student1.setClassroom(classroom);
        student1.setMarkList(markList1);

        student2 = new Student();
        student2.setFirstName("Tomasz");
        student2.setLastName("To");
        student2.setUserName("oto");
        student2.setClassroom(classroom);
        student2.setMarkList(markList2);

        Set<Student> studentSet = new HashSet<>();
        studentSet.add(student1);
        studentSet.add(student2);

        classroom.setClassroomNumber(1);
        classroom.setClassroomName("Pierwsza");
        classroom.setStudentsInClassroom(studentSet);

        mark1 = new Mark();
        mark1.setMarkName("mrn");
        mark1.setMarkValue(2);

        mark2 = new Mark();
        mark2.setMarkName("bdb");
        mark2.setMarkValue(5);

        markList1 = new ArrayList<>();
        markList2 = new ArrayList<>();

        Collections.addAll(markList1, mark1, mark1);
        Collections.addAll(markList2, mark2, mark2);

        student1.setMarkList(markList1);
        student2.setMarkList(markList2);

        markService.saveMark(mark1);
        markService.saveMark(mark2);
        classroomService.saveClassroom(classroom);
        studentService.saveStudent(student1);

    }

    @Test
    public void saveStudentTest() {
        //given
        //when
        Student savedStudent2 = studentService.saveStudent(student2);
        //then
        assertThat(2, is(studentService.getAll().size()));
        assertThat(student2, is(savedStudent2));
    }

    @Test
    public void updateByUserNameTest() {
        //given
        Student student = new Student();
        student.setFirstName("Jakub");
        student.setLastName("ub");
        student.setUserName("aja");
        student.setClassroom(classroom);
        student.setMarkList(Collections.emptyList());
        //when
        Student update = studentService.updateByUserName(student);
        //then
        assertThat(student, is(update));
        assertThat("ub", is(update.getLastName()));
        assertThat("Jakub", is(update.getFirstName()));
    }

    @Test
    public void deleteTest() {
        //given
        //when
        studentService.delete(student1);
        //then
        assertThat(0, is(studentService.getAll().size()));
    }

    @Test
    public void getAllTest() {
        //given
        studentService.saveStudent(student2);
        //when
        List<Student> all = studentService.getAll();
        //then
        assertThat(2, is(all.size()));
        assertThat(all, hasItem(student2));
    }

    @Test
    public void getStudentByIdTest() {
        //given
        Student student = studentService.saveStudent(student2);
        //when
        Student studentById = studentService.getStudentById(student.getIdStudent());
        //then
        assertThat(student2, is(studentById));
    }

    @Test
    public void getStudentByUserNameTest() {
        //given
        //when
        Student ajaStudent = studentService.getStudentByUserName("aja");
        //then
        assertThat("aja", is(ajaStudent.getUserName()));
    }

    @Test
    public void getClassroomStudentsByClassroomNumberDescLastNameTest() {
        //given
        Student student = new Student();
        student.setFirstName("Jakab2");
        student.setLastName("2a");
        student.setUserName("aj");
        student.setClassroom(classroom);
        student.setMarkList(Collections.emptyList());
        studentService.saveStudent(student);
        //when
        Collection<Student> allByOrderByClassroomDesc = studentService.getClassroomStudentsByClassroomNumberDescLastName(classroom.getClassroomNumber());

        //then
        assertThat(2, is(allByOrderByClassroomDesc.size()));
        assertThat(student1, is(allByOrderByClassroomDesc.toArray()[0]));
        assertThat(student, is(allByOrderByClassroomDesc.toArray()[1]));
        assertThat(student1, is(Collections.max(allByOrderByClassroomDesc, (student1, student2) -> {
            if (!student1.getLastName().equals(student2.getLastName()))
                return student1.getLastName().compareTo(student2.getLastName());
            else if (!student1.getLastName().equals(student2.getLastName()))
                return student1.getFirstName().compareTo(student2.getFirstName());
            return student1.getUserName().compareTo(student2.getUserName());
        })));
    }

    @Test
    public void getClassroomStudentsByClassroomNameDescLastNameTest() {
        //given
        studentService.saveStudent(student2);
        //when
        Collection<Student> classroomStudentsByClassroom = studentService.getClassroomStudentsByClassroomNameDescLastName(classroom.getClassroomName());
        //then
        assertThat(2, is(classroomStudentsByClassroom.size()));
        assertThat(classroomStudentsByClassroom, hasItem(student1));
        assertThat(classroomStudentsByClassroom, hasItem(student2));
    }

    @Test
    public void getStudentsWithGivenMarkTest() {
        //given
        Student student = studentService.saveStudent(student2);
        //when
        List<Student> studentsWithGivenMark = studentService.getStudentsWithGivenMark(mark2.getMarkValue());
        //then
        assertThat(studentsWithGivenMark, hasItem(student));
    }

    @Test
    public void getStudentsWithGivenMark_NoStudensForGivenMarkTest() {
        //given
        //when
        List<Student> studentsWithGivenMark = studentService.getStudentsWithGivenMark(mark2.getMarkValue());
        //then
        assertThat(0, is(studentsWithGivenMark.size()));
        assertThat(Collections.emptyList(), is(studentsWithGivenMark));
    }

    @Test
    public void getStudentsWithoutGivenMarkTest() {
        //given
        Student student = studentService.saveStudent(student2);
        //when
        List<Student> studentsWithoutGivenMark = studentService.getStudentsWithoutGivenMark(mark1.getMarkValue());
        //then
        assertThat(studentsWithoutGivenMark, hasItem(student));
    }

    @Test
    public void getStudentsWithoutGivenMark_NoStudentsForGicenMarkTest() {
        //given
        //when
        List<Student> studentsWithoutGivenMark = studentService.getStudentsWithoutGivenMark(mark1.getMarkValue());
        //then
        assertThat(0, is(studentsWithoutGivenMark.size()));
        assertThat(Collections.emptyList(), is(studentsWithoutGivenMark));
    }


}
