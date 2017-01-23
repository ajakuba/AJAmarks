package com.jakub.ajamarks.services.statisticservices;

import com.jakub.ajamarks.config.DataBaseForTestConfiguration;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;
import com.jakub.ajamarks.repositories.MarkRepository;
import com.jakub.ajamarks.repositories.StudentRepository;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
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
import static org.junit.Assert.assertTrue;

/**
 * Created by ja on 17.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseForTestConfiguration.class)
@Transactional
public class StatisticServiceTest {

    @Autowired
    private StatisticService statisticService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Student student5;
    private Student student6;
    private Classroom classroom1;
    private Classroom classroom2;
    private Set<Student> studentInClassroom1Set;
    private Set<Student> studentInClassroom2Set;
    private Set<Student> studentsWithNdstMarkSet;
    private Set<Student> studentsWithMrnMarkSet;
    private Set<Student> studentsWithDstMarkSet;
    private Set<Student> studentsWithDbMarkSet;
    private Set<Student> studentsWithBdbMarkSet;
    private Set<Student> studentsWithCelMarkSet;
    private Mark mark1, mark2, mark3, mark4, mark5, mark6;

    @Before
    public void testSetup(){

        mark1 = new Mark();
        mark1.setMarkName("ndst");
        mark1.setMarkValue(1);
        mark1.setStudentSet(studentsWithNdstMarkSet);

        mark2 = new Mark();
        mark2.setMarkName("mrn");
        mark2.setMarkValue(2);
        mark2.setStudentSet(studentsWithMrnMarkSet);

        mark3 = new Mark();
        mark3.setMarkName("dst");
        mark3.setMarkValue(3);
        mark3.setStudentSet(studentsWithDstMarkSet);

        mark4 = new Mark();
        mark4.setMarkName("db");
        mark4.setMarkValue(4);
        mark4.setStudentSet(studentsWithDbMarkSet);

        mark5 = new Mark();
        mark5.setMarkName("bdb");
        mark5.setMarkValue(5);
        mark5.setStudentSet(studentsWithBdbMarkSet);

        mark6 = new Mark();
        mark6.setMarkName("cel");
        mark6.setMarkValue(6);
        mark6.setStudentSet(studentsWithCelMarkSet);

        List<Mark> student1markList = new ArrayList<>();
        Collections.addAll(student1markList, mark1, mark2, mark3, mark4, mark5, mark6);
        List<Mark> student35markList = new ArrayList<>();
        Collections.addAll(student35markList, mark1, mark1, mark1, mark1, mark1, mark1);
        List<Mark> student4markList = new ArrayList<>();
        Collections.addAll(student4markList, mark3, mark3, mark3, mark3, mark3, mark3);
        List<Mark> student2markList = Collections.emptyList();

        classroom1 = new Classroom();
        classroom2 = new Classroom();

        studentsWithNdstMarkSet = new HashSet<>();
        studentsWithMrnMarkSet = new HashSet<>();
        studentsWithDstMarkSet = new HashSet<>();
        studentsWithDbMarkSet = new HashSet<>();
        studentsWithBdbMarkSet = new HashSet<>();
        studentsWithCelMarkSet = new HashSet<>();

        student1 = new Student();
        student1.setUserName("aja");
        student1.setFirstName("Jakub");
        student1.setLastName("Angel");
        student1.setClassroom(classroom1);
        student1.setMarkList(student1markList);

        student2 = new Student();
        student2.setUserName("oto");
        student2.setFirstName("Tomasz");
        student2.setLastName("Big");
        student2.setClassroom(classroom1);
        student2.setMarkList(student2markList);

        student3 = new Student();
        student3.setUserName("ete");
        student3.setFirstName("Edward");
        student3.setLastName("Theme");
        student3.setClassroom(classroom1);
        student3.setMarkList(student35markList);

        student4 = new Student();
        student4.setUserName("ibi");
        student4.setFirstName("Izabela");
        student4.setLastName("Break");
        student4.setClassroom(classroom2);
        student4.setMarkList(student4markList);

        student5 = new Student();
        student5.setUserName("ebi");
        student5.setFirstName("Izabela");
        student5.setLastName("Break");
        student5.setClassroom(classroom2);
        student5.setMarkList(student35markList);

        student6 = new Student();
        student6.setUserName("abi");
        student6.setFirstName("Izabela");
        student6.setLastName("Break");
        student6.setClassroom(classroom2);
        student6.setMarkList(student2markList);

        studentInClassroom1Set = new HashSet<>();
        studentInClassroom1Set.add(student1);
        studentInClassroom1Set.add(student2);
        studentInClassroom1Set.add(student3);

        studentInClassroom2Set = new HashSet<>();
        studentInClassroom2Set.add(student4);

        classroom1.setClassroomName("Pierwsza");
        classroom1.setClassroomNumber(1);
        classroom1.setStudentsInClassroom(studentInClassroom1Set);

        classroom2.setClassroomName("Druga");
        classroom2.setClassroomNumber(2);
        classroom2.setStudentsInClassroom(studentInClassroom2Set);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        classroomRepository.save(classroom1);
        classroomRepository.save(classroom2);
        markRepository.save(student1markList);

    }

    @Test
    public void countAverageMarkForUserTest(){
        //given
        double expectedAverage = 3.5;
        //when
        Double average = statisticService.countAverageMarkForUser(student1.getUserName());
        //then
        assertThat(expectedAverage, is(average));
    }

    @Test
    public void countAverageMarkForUser_WithNoMarksTest(){
        //given
        double expectedAverage = 0;
        //when
        Double userAverage = statisticService.countAverageMarkForUser(student2.getUserName());
        //then
        assertThat(expectedAverage, is(userAverage));
    }

    @Test
    public void countAverageMarkForClassroomTest(){
        //given
        double expectedAverage = 2;
        //when
        Double classroomAverage = statisticService.countAverageMarkForClassroom(classroom2.getClassroomName());
        System.out.println(classroomAverage);
        //then
        assertThat(expectedAverage, is(classroomAverage));

    }

    @Test
    public void countAverageMarkForSchoolTest(){
        //given
        double expectedAverage = 2.125;
        //when
        Double schoolAverage = statisticService.countAverageMarkForSchool();
        //then
        assertThat(expectedAverage, is(schoolAverage));
    }

    @Test
    public void isStudentAverageHigherThenInClassroomTest(){
        //given
        //when
        boolean studentAverageHigherThenInClassroom = statisticService.isStudentAverageHigherThenInClassroom(student1.getUserName());
        //then
        assertTrue(studentAverageHigherThenInClassroom);
    }

    @Test
    public void isStudentAverageHigherThenInAchoolTest(){
        //given
        //when
        boolean studentAverageHigherThenInClassroom = statisticService.isStudentAverageHigherThenInSchool(student1.getUserName());
        //then
        assertTrue(studentAverageHigherThenInClassroom);
    }

    @Test
    public void countHowManyStudentsAboveSchoolAverageTest(){
        //given
        int expectedValue = 2;
        //when
        Integer result = statisticService.countHowManyStudentsAboveSchoolAverage();
        //then
        assertThat(expectedValue, is(result));

    }

    @Test
    public void countHowManyStudentsAboveStudentAverageTest(){
        //given
        int expectedValue = 0;
        //when
        Integer result = statisticService.countHowManyStudentsAboveStudentAverage(student1.getUserName());
        //then
        assertThat(expectedValue, is(result));
    }

    @Test
    public void getStudentsAboveSchoolAverageTest(){
        //given
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student4);
        //when
        List<Student> studentsAboveSchoolAverage = statisticService.getStudentsAboveSchoolAverage();
        //then
        assertThat(studentList, is(studentsAboveSchoolAverage));
    }

    @Test
    public void getStudentsAboveStudentAverageTest(){
        //given
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student4);
        //when
        List<Student> studentsAboveStudentAverage = statisticService.getStudentsAboveStudentAverage(student3.getUserName());
        //then
        assertThat(studentList, is(studentsAboveStudentAverage));
    }

    @Test
    public void  sortStudentsByAverageAscTest(){
        //given
        //when
        Map<Double,  TreeSet<Student>> doubleStudentSetMap = statisticService.sortStudentsByAverageAsc();

        TreeSet<Student> students1 = doubleStudentSetMap.get(1d);
        TreeSet<Student> students2 = doubleStudentSetMap.get(3.5);
        TreeSet<Student> students3 = doubleStudentSetMap.get(3d);

        //then
        assertThat(students1, hasItem(student3));
        assertThat(students1, hasItem(student5));
        assertThat(students2, hasItem(student1));
        assertThat(students3, hasItem(student4));
        assertThat(student5, is(students1.first()));
        assertThat(student3, is(students1.last()));
        assertThat(2, is(students1.size()));
        assertThat(1, is(students2.size()));
        assertThat(1, is(students3.size()));


    }

    @Test
    public void getFromSchoolMarkStudentListMapTest(){
        //given
        Set<Student> expectedStudentSet1 = new TreeSet<>();
        expectedStudentSet1.add(student1);
        expectedStudentSet1.add(student3);
        expectedStudentSet1.add(student5);

        Set<Student> expectedStudentSet3 = new TreeSet<>();
        expectedStudentSet3.add(student1);
        expectedStudentSet3.add(student4);
        //when
        Map<Mark, Set<Student>> fromSchoolMarkStudentListMap = statisticService.getFromSchoolMarkStudentListMap();
        Set<Student> studentSet1 = fromSchoolMarkStudentListMap.get(mark1);
        Set<Student> studentSet2 = fromSchoolMarkStudentListMap.get(mark2);
        Set<Student> studentSet3 = fromSchoolMarkStudentListMap.get(mark3);
        Set<Student> studentSet4 = fromSchoolMarkStudentListMap.get(mark4);
        Set<Student> studentSet5 = fromSchoolMarkStudentListMap.get(mark5);
        Set<Student> studentSet6 = fromSchoolMarkStudentListMap.get(mark6);


        //then
        assertThat(3, is(studentSet1.size()));
        assertThat(1, is(studentSet2.size()));
        assertThat(2, is(studentSet3.size()));
        assertThat(1, is(studentSet4.size()));
        assertThat(1, is(studentSet5.size()));
        assertThat(1, is(studentSet6.size()));
        assertThat(expectedStudentSet1, is(studentSet1));
        assertThat(expectedStudentSet3, is(studentSet3));
    }

    @Test
    public void getFromClassroomMarkStudentListMapTest(){
        Set<Student> expectedStudentSet13 = new TreeSet<>();
        expectedStudentSet13.add(student1);
        expectedStudentSet13.add(student3);

        Map<Mark, Set<Student>> fromClassroomMarkStudentListMap = statisticService.getFromClassroomMarkStudentListMap(classroom1.getClassroomName());
        Set<Student> studentSet1 = fromClassroomMarkStudentListMap.get(mark1);
        Set<Student> studentSet2 = fromClassroomMarkStudentListMap.get(mark2);
        Set<Student> studentSet3 = fromClassroomMarkStudentListMap.get(mark3);
        Set<Student> studentSet4 = fromClassroomMarkStudentListMap.get(mark4);
        Set<Student> studentSet5 = fromClassroomMarkStudentListMap.get(mark5);
        Set<Student> studentSet6 = fromClassroomMarkStudentListMap.get(mark6);

        //then
        assertThat(2, is(studentSet1.size()));
        assertThat(1, is(studentSet2.size()));
        assertThat(1, is(studentSet3.size()));
        assertThat(1, is(studentSet4.size()));
        assertThat(1, is(studentSet5.size()));
        assertThat(1, is(studentSet6.size()));
        assertThat(expectedStudentSet13, is(studentSet1));
    }

    @Test
    public void countQuantityOfEachMarkForSchoolTest(){
        //given
        int markQuantity1 = 13;
        int markQuantity2 = 1;
        int markQuantity3 = 7;
        int markQuantity4 = 1;
        int markQuantity5 = 1;
        int markQuantity6 = 1;

        Map<Mark, Integer> markIntegerMap = statisticService.countQuantityOfEachMarkForSchool();

        Integer integer1 = markIntegerMap.get(mark1);
        Integer integer2 = markIntegerMap.get(mark2);
        Integer integer3 = markIntegerMap.get(mark3);
        Integer integer4 = markIntegerMap.get(mark4);
        Integer integer5 = markIntegerMap.get(mark5);
        Integer integer6 = markIntegerMap.get(mark6);

        assertThat(markQuantity1, is(integer1));
        assertThat(markQuantity2, is(integer2));
        assertThat(markQuantity3, is(integer3));
        assertThat(markQuantity4, is(integer4));
        assertThat(markQuantity5, is(integer5));
        assertThat(markQuantity6, is(integer6));
    }

}
