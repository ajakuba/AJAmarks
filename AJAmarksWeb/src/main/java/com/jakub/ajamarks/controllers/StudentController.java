package com.jakub.ajamarks.controllers;


import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.services.showdataservices.StudentService;
import org.springframework.beans.factory.annotation.Autowired;


import com.jakub.ajamarks.entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("student")
    public ResponseEntity addStudent(
            @RequestParam String userName,
            @RequestParam String firstName,
            @RequestParam String lastName) {

        Student student = new Student();
        student.setUserName(userName);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setClassroom(new Classroom());
        student.setStudentMarks(Collections.emptyList());

        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("studentNew")
    public ResponseEntity createStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("student")
    public ResponseEntity updateStudent(@PathVariable long studentId, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(studentId, student);
            return new ResponseEntity(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("student/{userName}")
    public ResponseEntity deleteStudent(@PathVariable String userName) {
        try {
            Student studentByUserName = studentService.getStudentByUserName(userName);
            studentService.delete(studentByUserName);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("students")
    public ResponseEntity showAllStudents() {
        try {
            Collection<Student> all = studentService.getAll();
            return new ResponseEntity(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("student/{id:\\d+}")
    public ResponseEntity showStudentByUserId(@PathVariable Long studentId) {
        try {
            Student studentById = studentService.getStudentById(studentId);
            return new ResponseEntity(studentById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("student/{studentUser:[A-Za-z]+}")
    public ResponseEntity showStudentByUserName(@PathVariable String studentUser) {
        try {
            Student studentByUserName = studentService.getStudentByUserName(studentUser);
            return new ResponseEntity(studentByUserName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("studentsWithGivenMarkValue/{markValue}")
    public ResponseEntity showStudentsWithGivenMarkValue(@PathVariable int markvalue){
        try{
            List<Student> studentsWithGivenMarkValue = studentService.getStudentsWithGivenMarkValue(markvalue);
            Collections.sort(new ArrayList<>(studentsWithGivenMarkValue));
            return new ResponseEntity(studentsWithGivenMarkValue, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("studentsWithoutGivenMarkValue/{markValue}")
    public ResponseEntity showStudentsWithoutGivenMarkValue(@PathVariable int markvalue){
        try{
            List<Student> studentsWithoutGivenMarkValue = studentService.getStudentsWithoutGivenMarkValue(markvalue);
            Collections.sort(new ArrayList<>(studentsWithoutGivenMarkValue));
            return new ResponseEntity(studentsWithoutGivenMarkValue, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroomStudentsByClassroomNumberDescLastName/{classroomNumber}")
    public ResponseEntity showStudentsByClassroomNumber(@PathVariable int classroomNumber) {
        try {
            List<Student> classroomStudentsByClassroomNumberDescLastName = studentService.getClassroomStudentsByClassroomNumberDescLastName(classroomNumber);
            return new ResponseEntity (classroomStudentsByClassroomNumberDescLastName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity (e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("classroomStudentsByClassroomNameDescLastName/{classroomName}")
    public     ResponseEntity showStudentsByClassroomName(@PathVariable String classroomName) {
        try {
            List<Student> classroomStudentsByClassroomNumberDescLastName = studentService.getClassroomStudentsByClassroomNameDescLastName(classroomName);
            return new ResponseEntity(classroomStudentsByClassroomNumberDescLastName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
