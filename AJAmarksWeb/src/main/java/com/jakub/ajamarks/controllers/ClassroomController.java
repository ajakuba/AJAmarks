package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.services.showdataservices.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ja on 30.01.17.
 */
@Controller
@RequestMapping("api/classroom")
public class ClassroomController {

    @Autowired
    ClassroomService classroomService;

    @RequestMapping("getClassrooms")
    public @ResponseBody List<Classroom> showAllClassrooms(){
        return classroomService.getAllByClassroomNameAsc();
    }


}
