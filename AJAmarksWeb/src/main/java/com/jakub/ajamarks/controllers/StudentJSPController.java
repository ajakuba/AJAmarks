package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.StudentService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("restapi/student")
public class StudentJSPController {

    @Autowired
    StudentService studentService;


    @GetMapping("/studentAdmissionForm")
    public ModelAndView getStudentAdmissionForm() {
        ModelAndView model = new ModelAndView("AdmissionForm");
        return model;
    }

//    @RequestMapping(value = "/submitionStudentAdmissionForm.html", method = RequestMethod.POST)
//    public ModelAndView addStudent (
//
//            @RequestParam (value = "userName", defaultValue = "Default") String userName,
//            @RequestParam (value = "firstName", defaultValue = "Default") String firstName,
//            @RequestParam (value = "lastName", defaultValue = "Default") String lastName
//            ){
//
//        Student student = new Student();
//        student.setUserName(userName);
//        student.setFirstName(firstName);
//        student.setLastName(lastName);
//
//        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
//        modelAndView.addObject("msg", "brawo dodales: "+student);
//
//        studentService.saveStudent(student);
//
//        return modelAndView;
//    }

//    //second wersion of formula usin @RequestParam Map<Strin Strin> map
//    @PostMapping("/submitionStudentAdmissionForm.html")
//    public ModelAndView addStudent2 (@RequestParam Map<String, String> map    ){
//        String userName = map.get("userName");
//        String firstName = map.get("firstName");
//        String lastName = map.get("lastName");
//
//        Student student1 = new Student();
//        student1.setUserName(userName);
//        student1.setFirstName(firstName);
//        student1.setLastName(lastName);
//
//        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
//        modelAndView.addObject("headerMessage", "brawo dodales: ");
//        modelAndView.addObject("student1", student1);
//
//        studentService.saveStudent(student1);
//
//        return modelAndView;
//    }

    //third wersion of formula using @ModelAtribute
    @PostMapping("/submitionStudentAdmissionForm.html")
    public ModelAndView addStudent3(@ModelAttribute Student student1) {

        studentService.saveStudent(student1);

        ModelAndView model = new ModelAndView("AdmissionSuccess");

        return model;

    }

    //@ModelAtribute on method gives possibility to add to model some value - "wow" wchich will be dispalyd in jsp pages from two above methods
    @ModelAttribute
    public void addingParams(Model model1) {
        model1.addAttribute("headerMessage", "Wow");

    }


}
