package com.jakub.ajamarks.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ja on 29.01.17.
 */
@Controller
public class EntryController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/entry")
    public String login(Model model){

        logger.info("Witaj");
        model.addAllAttributes(new ArrayList<>());
        return "entry";
    }
}
