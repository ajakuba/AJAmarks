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
 * Created by ja on 17.01.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataBaseForTestConfiguration.class})
@Transactional
public class MarkServiceIntegrationTest {

    @Autowired
    MarkService markService;
    @Autowired
    StudentService studentService;
    @Autowired
    ClassroomService classroomService;

    private Mark mark1, mark2;
    private Classroom classroom;

    @Before
    public void setup() {

        Set<Student> studentsetMrn = new HashSet<>();
        Set<Student> studentSetBdb = new HashSet<>();
        Set<Student> studentSet = new HashSet<>();

        mark1 = new Mark();
        mark1.setMarkName("mrn");
        mark1.setMarkValue(2);
        mark1.setStudentSet(studentsetMrn);

        mark2 = new Mark();
        mark2.setMarkName("bdb");
        mark2.setMarkValue(5);
        mark2.setStudentSet(studentSetBdb);

        List<Mark> markList1 = new ArrayList<>();
        List<Mark> markList2 = new ArrayList<>();

        Collections.addAll(markList1, mark1, mark1);
        Collections.addAll(markList2, mark1, mark2);

        Student student1 = new Student();
        student1.setFirstName("Jakub");
        student1.setLastName("Ja");
        student1.setUserName("aja");
        student1.setClassroom(classroom);
        student1.setMarkList(markList1);

        Student student2 = new Student();
        student2.setFirstName("Tomasz");
        student2.setLastName("To");
        student2.setUserName("oto");
        student2.setClassroom(classroom);
        student2.setMarkList(markList2);

        studentsetMrn.add(student1);
        studentsetMrn.add(student2);
        studentSetBdb.add(student2);


        studentSet.add(student1);
        studentSet.add(student2);

        classroom = new Classroom();
        classroom.setClassroomNumber(1);
        classroom.setClassroomName("Pierwsza");
        classroom.setStudentsInClassroom(studentSet);

        markService.saveMark(mark1);

        classroomService.saveClassroom(classroom);
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);


    }

    @Test
    public void saveMarkTest() {
        //given
        //when
        Mark mark = markService.saveMark(mark2);
        //then
        assertThat(2, is(markService.getAll().size()));
        assertThat(mark2, is(mark));
    }

    @Test
    public void updateByMarkValueTest() {
        //given
        Mark mark = new Mark();
        mark.setMarkValue(2);
        mark.setMarkName("mierny");
        //when
        Mark update = markService.updateByMarkValue(mark);
        //then
        assertThat(mark, is(update));
        assertThat("mierny", is(update.getMarkName()));
    }

    @Test
    public void deleteTest() {
        //given
        //when
        markService.delete(mark1);
        //then
        assertThat(0, is(markService.getAll().size()));
    }

    @Test
    public void getAllTest() {
        //given
        markService.saveMark(mark2);
        //when
        List<Mark> all = markService.getAll();
        //the
        assertThat(2, is(all.size()));
        assertThat(all, hasItem(mark1));
        assertThat(all, hasItem(mark2));
    }

    @Test
    public void getByMarkValueTest() {
        //gicen
        //when
        Mark byMarkValue = markService.getByMarkValue(2);
        //then
        assertThat(mark1, is(byMarkValue));
        assertThat(2, is(byMarkValue.getMarkValue()));
    }

    @Test
    public void getByMarkNameTest() {
        //gicen
        //when
        Mark byMarkName = markService.getByMarkName("mrn");
        //then
        assertThat(mark1, is(byMarkName));
        assertThat("mrn", is(byMarkName.getMarkName()));
    }

    @Test
    public void getStudentsByGivenMarkValueTest() {
        //given
        markService.saveMark(mark2);
        //when
        Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkValue(5);
        //then
        assertThat(1, is(studentsByGivenMarkValue.size()));
    }

    @Test
    public void getStudentsByGivenMarkValue_NoGivenMarkVaslueTest() {
        //given
        //when
        Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkValue(10);
        //then
        assertThat(0, is(studentsByGivenMarkValue.size()));
    }

    @Test
    public void getStudentsByGivenMarkValue_NoStudentsForGivenMarkVaslueTest() {
        //given
        //when
        Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkValue(5);
        //then
        assertThat(0, is(studentsByGivenMarkValue.size()));
    }


    @Test
    public void getStudentsByGivenMarkNameTest() {
        //given
        markService.saveMark(mark2);
        //when
        Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkName("mrn");
        //then
        assertThat(2, is(studentsByGivenMarkValue.size()));
    }

    @Test
    public void getStudentsByGivenMarkName_NoGivenMarkNameTest() {
        //given
        //when
        Set<Student> studentsByGivenMarkName = markService.getStudentsByGivenMarkName("dst");
        //then
        assertThat(0, is(studentsByGivenMarkName.size()));
    }

    @Test
    public void getStudentsByGivenMarkName_NoStudentsForGivenMarkNameTest() {
        //given
        //when
        Set<Student> studentsByGivenMarkName = markService.getStudentsByGivenMarkName("bdb");
        //then
        assertThat(0, is(studentsByGivenMarkName.size()));
    }

}
