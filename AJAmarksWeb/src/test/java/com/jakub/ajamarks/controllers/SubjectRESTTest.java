package com.jakub.ajamarks.controllers;


import com.jakub.ajamarks.config.WebAppConfigurationContext;
import com.jakub.ajamarks.config.WebAppTestConfigurationContext;
import com.jakub.ajamarks.entities.Subject;
import com.jakub.ajamarks.services.showdataservices.SubjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppTestConfigurationContext.class})
@WebAppConfiguration(value = "src/main/webapp")
public class SubjectRESTTest {


    private MockMvc mockMvc;

//    @Autowired
//    SubjectService mockSubjectService;

    private SubjectController subjectController;


    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {

        subjectController = new SubjectController();
        subjectController.subjectService = mock(SubjectService.class);

        //because mocking is managed by Spring we should invoke reset() to clean Mocking
  //      Mockito.reset(mockSubjectService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


    }

    @Test
    public void showAllTest() throws Exception {

        Subject subject1 = new Subject();
        subject1.setSubjectId(1);
        subject1.setSubjectName("Matematyka");

        Subject subject2 = new Subject();
        subject1.setSubjectId(2);
        subject1.setSubjectName("Polski");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        when(subjectController.subjectService.getAll()).thenReturn(subjects);

        mockMvc.perform(get("/api/subject/subject").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
            //    .andExpect(content().contentType(MediaType.APPLICATION_JSON.getType()))
                .andExpect(jsonPath("$[0].subjectName", is("Matematyka")))
                .andExpect(jsonPath("$[1].subjectName", is("Historia")));


        verify(subjectController.subjectService).getAll();

    }


}
