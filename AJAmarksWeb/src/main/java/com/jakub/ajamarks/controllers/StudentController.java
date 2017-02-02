package com.jakub.ajamarks.controllers;


import com.jakub.ajamarks.services.showdataservices.StudentService;
import org.springframework.beans.factory.annotation.Autowired;


import com.jakub.ajamarks.entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("astudent")
    public ResponseEntity addStudent(
            @RequestParam String userName,
            @RequestParam String firstName,
            @RequestParam String lastName) {

        Student student = new Student();
        student.setUserName(userName);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity(savedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("cstudent")
    public ResponseEntity createStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity(savedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("uStudent")     
    public ResponseEntity updateStudent(@RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateByUserName(student);
            return new ResponseEntity(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("dStudent/{userName}")
    public void deleteStudent(@PathVariable String userName) {
        Student studentByUserName = studentService.getStudentByUserName(userName);
        studentService.delete(studentByUserName);
    }

    @GetMapping("students")
    public List<Student> showAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("student/{id:\\d+}")
    public ResponseEntity showStudentByUserId(@PathVariable Long studentId) {
        try {
            Student studentById = studentService.getStudentById(studentId);
            return new ResponseEntity(studentById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("student/{studentUser:[A-Za-z]+}")
    public ResponseEntity showStudentByUserName(@PathVariable String studentUser) {
        try {
            Student studentByUserName = studentService.getStudentByUserName(studentUser);
            return new ResponseEntity(studentByUserName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("studentsWithGivenMarkValue/{markValue}")
    public ResponseEntity getStudentsWithGivenMarkValue(@PathVariable int markvalue){
        try{
            List<Student> studentsWithGivenMarkValue = studentService.getStudentsWithGivenMarkValue(markvalue);
            return new ResponseEntity(studentsWithGivenMarkValue, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("studentsWithoutGivenMarkValue/{markValue}")
    public ResponseEntity getStudentsWithoutGivenMarkValue(@PathVariable int markvalue){
        try{
            List<Student> studentsWithGivenMarkValue = studentService.getStudentsWithoutGivenMarkValue(markvalue);
            return new ResponseEntity(studentsWithGivenMarkValue, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("classroomStudentsByClassroomNumberDescLastName/{classroomNumber}")
    public List<Student> getStudentsByClassroomNumber(@PathVariable int classroomNumber) {
        List<Student> classroomStudentsByClassroomNumberDescLastName = studentService.getClassroomStudentsByClassroomNumberDescLastName(classroomNumber);
        return classroomStudentsByClassroomNumberDescLastName;
    }

    @GetMapping("classroomStudentsByClassroomNameDescLastName/{classroomName}")
    public     List<Student> getStudentsByClassroomName(@PathVariable String classroomName) {
        List<Student> classroomStudentsByClassroomNumberDescLastName = studentService.getClassroomStudentsByClassroomNameDescLastName(classroomName);
        return classroomStudentsByClassroomNumberDescLastName;
    }

}
