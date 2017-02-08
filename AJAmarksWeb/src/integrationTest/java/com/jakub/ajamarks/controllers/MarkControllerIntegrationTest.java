package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.config.ForWebTestConfiguration;
import com.jakub.ajamarks.config.JsonConverter;
import com.jakub.ajamarks.config.WebAppTestConfigurationContext;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppTestConfigurationContext.class})
@WebAppConfiguration
@TestPropertySource("/jpaforWebtestsproperties.properties")
@Transactional
public class MarkControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private MarkController markController;
    @Autowired
    private ClassroomController classroomController;
    @Autowired
    private StudentController studentController;
    private Mark mark1, mark2, mark3;
    private Mark localMark;
    private Classroom classroom;

    @Before
    public void setUp() {

        Set<Student> studentsetMrn = new HashSet<>();
        Set<Student> studentSetBdb = new HashSet<>();
        Set<Student> studentSet = new HashSet<>();

        mark1 = new Mark();
        mark1.setMarkName("mrn");
        mark1.setMarkValue(2);
        mark1.setStudentsWithMark(studentsetMrn);

        mark2 = new Mark();
        mark2.setMarkName("bdb");
        mark2.setMarkValue(5);
        mark2.setStudentsWithMark(studentSetBdb);

        mark3 = new Mark();
        mark3.setMarkName("db");
        mark3.setMarkValue(4);
        mark3.setStudentsWithMark(null);

        List<Mark> markList1 = new ArrayList<>();
        List<Mark> markList2 = new ArrayList<>();

        Collections.addAll(markList1, mark1, mark1);
        Collections.addAll(markList2, mark1, mark2);

        Student student1 = new Student();
        student1.setFirstName("Jakub");
        student1.setLastName("Ja");
        student1.setUserName("aja");
        student1.setClassroom(classroom);
        student1.setStudentMarks(markList1);

        Student student2 = new Student();
        student2.setFirstName("Tomasz");
        student2.setLastName("To");
        student2.setUserName("oto");
        student2.setClassroom(classroom);
        student2.setStudentMarks(markList2);

        studentsetMrn.add(student1);
        studentsetMrn.add(student2);
        studentSetBdb.add(student2);

        studentSet.add(student1);
        studentSet.add(student2);

        classroom = new Classroom();
        classroom.setClassroomNumber(1);
        classroom.setClassroomName("Pierwsza");
        classroom.setStudentsInClassroom(studentSet);

        ResponseEntity mark = markController.createMark(mark1);
        System.out.println(mark);
        ResponseEntity mark4 = markController.createMark(mark2);
        System.out.println(mark4);

        classroomController.createClassroom(classroom);
        studentController.createStudent(student1);
        studentController.createStudent(student2);

        localMark = new Mark();
        localMark.setIdMark(33L);
        localMark.setMarkValue(3);
        localMark.setMarkName("dst");
        localMark.setStudentsWithMark(Collections.emptySet());

        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void addMarkTest() throws Exception {
        mockMvc.perform(post("/api/mark/mark?markName=dst&markValue=3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.markName", is("dst")));
        checkSize(4);
    }

    @Test
    public void createMark() throws Exception {
        mockMvc.perform(post("/api/mark/markNew").contentType(MediaType.APPLICATION_JSON).content(JsonConverter.convertObjectToJsonBytes(localMark)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("markValue").value(localMark.getMarkValue()));
        checkSize(4);
    }

    @Test
    public void deleteMark() throws Exception {
        checkSize(3);

        mockMvc.perform(delete("/api/mark/mark/{markValue}", 6).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        checkSize(2);
    }

    @Test
    public void updateMark() throws Exception {

        Mark localMark2 = new Mark();
        localMark2.setIdMark(101l);
        localMark2.setMarkValue(6);
        localMark2.setMarkName("celujący");
        localMark2.setStudentsWithMark(Collections.emptySet());

        mockMvc.perform(put("/api/mark/mark/{idMark}", localMark2.getIdMark()).contentType(MediaType.APPLICATION_JSON).content(JsonConverter.convertObjectToJsonBytes(localMark2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.markName", is("celujący")));

        checkSize(3);
    }

    @Test
    public void showAllMarks() throws Exception {
        mockMvc.perform(get("/api/mark/mark").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void showByMarkValue() throws Exception {
        mockMvc.perform(get("/api/mark/mark/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("markValue").value(mark1.getMarkValue()));
    }

    @Test
    public void showByMarkName() throws Exception {
        mockMvc.perform(get("/api/mark/mark/{markName}", "cel").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("markName", is("cel")))
                .andExpect(jsonPath("markValue", is(6)));
    }

    @Test
    public void showStudentsByGivenMarkValue() throws Exception {
        mockMvc.perform(get("/api/mark/studentsWithGivenMarkValue/{markValue}", 5).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Tomasz")))
                .andExpect(jsonPath("$[0].userName", is("oto")))
                .andExpect(jsonPath("$[0].lastName", is("To")))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void showStudentsByGivenMarkName() throws Exception {
        mockMvc.perform(get("/api/mark/studentsWithGivenMarkName/{markName}", "MRN").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Jakub")))
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    private void checkSize(int expectedValue) throws Exception {
        mockMvc.perform(get("/api/mark/mark").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedValue)));
    }

}