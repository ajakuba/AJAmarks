package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ja on 30.01.17.
 */
@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    @Autowired
    ClassroomService classroomService;

    @PostMapping("classroom")
    public ResponseEntity addClassroom(
            @RequestParam int classroomNumber,
            @RequestParam String classroomName) {

        Classroom classroom = new Classroom();
        classroom.setClassroomNumber(classroomNumber);
        classroom.setClassroomName(classroomName);
        classroom.setStudentsInClassroom(Collections.emptySet());

        try {
            Classroom savedClassroom = classroomService.saveClassroom(classroom);
            return new ResponseEntity(savedClassroom, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("classroomNew")
    public ResponseEntity createClassroom(@RequestBody Classroom classroom) {
        try {
            Classroom savedClassroom = classroomService.saveClassroom(classroom);
            return new ResponseEntity(savedClassroom, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("classroom/{classroomId://d}")
    public ResponseEntity updateClassroom(@PathVariable long classroomId, @RequestBody Classroom classroom) {
        try {
            Classroom updatedClassroom = classroomService.updateClassroom(classroomId, classroom);
            return new ResponseEntity(updatedClassroom, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("classroom/{classroomNumber}")
    public ResponseEntity deleteClassroom(@PathVariable int classNumber) {
        try {
            Classroom classroomByNumber = classroomService.getClassroomByNumber(classNumber);
            classroomService.delete(classroomByNumber);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/classroom")
    public ResponseEntity showAllClassrooms() {
        try {
            List<Classroom> allByClassroomNameAsc = classroomService.getAllByClassroomNameAsc();
            return new ResponseEntity(allByClassroomNameAsc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroomById/{classroomID}")
    public ResponseEntity showClassroomById(@PathVariable long classroomID){
        try {
            Classroom classroomById = classroomService.getClassroomById(classroomID);
            return new ResponseEntity(classroomById, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroom/{classroomNumber:\\d}")
    public ResponseEntity showClassroomByNumber(@PathVariable int classroomNumber) {
        try {
            Classroom classroomByNumber = classroomService.getClassroomByNumber(classroomNumber);
            return new ResponseEntity(classroomByNumber, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroom/{classroomName:[A-Za-z]+}")
    public ResponseEntity showClassroomByName(@PathVariable String classroomName){
        try {
            Classroom classroomByName = classroomService.getClassroomByName(classroomName);
            return new ResponseEntity(classroomByName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroomStudents/{classroomNumber:\\d}")
    public ResponseEntity showClassroomStudentsByClassroomNumber(@PathVariable int classroomNumber){
        try {
            Set<Student> classroomStudentsByClassroomNumber = classroomService.getClassroomStudentsByClassroomNumber(classroomNumber);
            ArrayList<Student> students = new ArrayList<>(classroomStudentsByClassroomNumber);
            Collections.sort(students);
            return new ResponseEntity(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroomStudents/{classroomName:[A-Za-z]+}")
    public ResponseEntity showClassroomStudentsByClassroomName(@PathVariable String classroomName){
        try {
            Set<Student> classroomStudentsByClassroomName = classroomService.getClassroomStudentsByClassroomName(classroomName);
            ArrayList<Student> students = new ArrayList<>(classroomStudentsByClassroomName);
            Collections.sort(students);
            return new ResponseEntity(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }



}
