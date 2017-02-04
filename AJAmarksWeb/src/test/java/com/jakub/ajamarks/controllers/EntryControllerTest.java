package com.jakub.ajamarks.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ja on 03.02.17.
 */
public class EntryControllerTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void login() throws Exception {
        //given
        EntryController entryController = new EntryController();
        Model model = mock(Model.class);
        when(model.addAttribute("Hallo", "Witaj")).thenReturn(model);
        //when
        String login = entryController.login(model);
        //then
        assertThat(login, is("entry"));
    }

}