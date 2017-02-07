package com.jakub.ajamarks.controllers;


import com.jakub.ajamarks.config.DataBaseForTestConfiguration;
import com.jakub.ajamarks.config.JsonConverter;
import com.jakub.ajamarks.config.WebAppConfigurationContext;
import com.jakub.ajamarks.config.WebAppTestConfigurationContext;
import com.jakub.ajamarks.entities.Subject;
import com.jakub.ajamarks.services.showdataservices.SubjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppTestConfigurationContext.class, DataBaseForTestConfiguration.class})
@WebAppConfiguration
@EnableWebMvc
@Transactional
public class SubjectControllerIntegrationTest {

    @Autowired
    SubjectService subjectService;

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void addSubjectTest() throws Exception {
        mockMvc.perform(post("/api/subject/subject?name=Biologia").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("subjectName", is("Biologia")));
        checkSize(2);
    }

    @Test
    public void createSubjectTest() throws Exception {
        Subject subject = new Subject();
        subject.setSubjectId(1);
        subject.setSubjectName("Informatyka");

        mockMvc.perform(post("/api/subject/subjectNew").contentType(MediaType.APPLICATION_JSON).content(JsonConverter.convertObjectToJsonBytes(subject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("subjectName").value(subject.getSubjectName()));
        checkSize(2);
    }

    @Test
    public void updateSubjectTest() throws Exception{
        Subject subject = new Subject();
        subject.setSubjectId(101);
        subject.setSubjectName("Matematyka2");

        mockMvc.perform(put("/api/subject/subject/{subjectId}",101L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.convertObjectToJsonBytes(subject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectName", is("Matematyka2")));
        checkSize(1);
    }

    @Test
    public void deleteSubject() throws Exception{
        mockMvc.perform(delete("/api/subject/subject/{subjectId}", 101L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        checkSize(0);
    }

    @Test
    public void showAllTest() throws Exception {
        mockMvc.perform(get("/api/subject/subject").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].subjectName", is("Matematyka")));
        checkSize(1);
    }

    @Test
    public void showSubjectByIdTest() throws Exception{

        mockMvc.perform(get("/api/subject/subject/{subjectId}", 101L ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId", is(101)))
                .andExpect(jsonPath("$.subjectName", is("Matematyka")));
        checkSize(1);
    }


    private void checkSize(int expectedValue) throws Exception {
        mockMvc.perform(get("/api/subject/subject").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedValue)));
    }


}


