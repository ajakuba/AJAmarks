package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.ClassroomService;
import com.jakub.ajamarks.services.showdataservices.MarkService;
import com.jakub.ajamarks.services.showdataservices.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by ja on 30.01.17.
 */

@Controller
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    MarkService markService;

    @Autowired
    StudentService studentService;

    @Autowired
    ClassroomService classroomService;


    @GetMapping("getStudents")
    public @ResponseBody List<Student> showAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("getStudent/{studentUser}")
    public @ResponseBody Student showStudent(@PathVariable String studentUser){
       return studentService.getStudentByUserName(studentUser);
    }

    @DeleteMapping("deleteStudent/{userName}")
    public @ResponseBody void deletStucent(@PathVariable String userName){
        Student studentByUserName = studentService.getStudentByUserName(userName);
        studentService.delete(studentByUserName);
    }

    @GetMapping("getStudentsByClassroomId/{classroomId}")
    public Set<Student> get(@PathVariable int classroomId){

        return classroomService.getClassroomById(classroomId).getStudentsInClassroom();


    }




}
