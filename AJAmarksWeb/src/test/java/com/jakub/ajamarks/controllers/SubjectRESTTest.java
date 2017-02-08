package com.jakub.ajamarks.controllers;


import com.jakub.ajamarks.config.ForTestConfiguration;
import com.jakub.ajamarks.config.StartConfiguration;
import com.jakub.ajamarks.config.WebAppConfigurationContext;
import com.jakub.ajamarks.config.WebAppTestConfigurationContext;
import com.jakub.ajamarks.entities.Subject;
import com.jakub.ajamarks.services.showdataservices.SubjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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
@ContextConfiguration(classes = {WebAppTestConfigurationContext.class, ForTestConfiguration.class})
@WebAppConfiguration(value = "src/main/webapp")
public class SubjectRESTTest {

    private MockMvc mockMvc;

//    @Autowired
//    SubjectService mockSubjectService;

    private SubjectService mockSubjectService;


    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {

        mockSubjectService = mock(SubjectService.class);

        //because mocking is managed by Spring we should invoke reset() to clean Mocking
       // Mockito.reset(mockSubjectService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


    }

    @Test
    public void showSubjectTest() throws Exception {

        Subject subject1 = new Subject();
        subject1.setSubjectId(1L);
        subject1.setSubjectName("Matematyka");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);

        when(mockSubjectService.getSubjectById(1L)).thenReturn(subject1);

        ResultActions perform = mockMvc.perform(get("/api/subject/subject/{subjectId}", 1L).contentType(MediaType.APPLICATION_JSON));

        System.out.println("!!!!!!!!!!!!!!!!!!"+perform.andReturn().getResponse().getContentAsString());

        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId", is(1L)))
                .andExpect(jsonPath("$.subjectName", is("Matematyka")));


        verify(mockSubjectService).getSubjectById(1L);

    }


}
