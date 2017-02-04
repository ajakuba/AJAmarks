package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.StudentService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ja on 03.02.17.
 */
public class StudentJSPControllerTest {
    @Test
    public void getStudentAdmissionForm() throws Exception {
        StudentJSPController studentJSPController = new StudentJSPController();
        ModelAndView studentAdmissionForm = studentJSPController.getStudentAdmissionForm();
        assertThat("AdmissionForm", is(studentAdmissionForm.getViewName()));
    }

    @Test
    public void addStudent3() {
        //given
        Student student = new Student();
        student.setUserName("JA");
        student.setFirstName("Jak");
        student.setLastName("Mak");
        StudentJSPController studentJSPController = new StudentJSPController();
        studentJSPController.studentService = mock(StudentService.class);

        when(studentJSPController.studentService.saveStudent(student)).thenReturn(student);
        //when
        ModelAndView modelAndView = studentJSPController.addStudent3(student);
        //then
        assertThat("AdmissionSuccess", is(modelAndView.getViewName()));
    }

}