package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.MarkService;
import com.jakub.ajamarks.services.showdataservices.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("api/mark")
public class MarkController {

    @Autowired
    MarkService markService;
    @Autowired
    StudentService studentService;

    @PostMapping("aMark")
    public
    @ResponseBody
    HttpEntity<Mark> addMark(
            @RequestParam String markName,
            @RequestParam int markValue) {
        try {
            Mark mark = new Mark();
            mark.setMarkName(markName);
            mark.setMarkValue(markValue);

            markService.saveMark(mark);
            return new ResponseEntity<>(mark, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("cMark")
    public
    @ResponseBody
    HttpEntity<Mark> createMark(@RequestBody Mark mark) {
        try {
            Mark savedMark = markService.saveMark(mark);
            return new ResponseEntity<>(savedMark, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("dMark/{markValue}")
    public
    @ResponseBody
    void deleteMark(
            @PathVariable Integer markValue) {
        Mark mark = markService.getByMarkValue(markValue);
        markService.delete(mark);
    }

    @PutMapping("uMark")
    public ResponseEntity updateMark(@RequestBody Mark mark) {
        try {
            Mark updatedMark = markService.updateMark(mark);
            return new ResponseEntity(updatedMark, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("marks")
    public
    @ResponseBody
    List<Mark> showAllMarks() {
        return markService.getAll();
    }


    @GetMapping("mark/{markValue:\\d}")
    public
    @ResponseBody
    ResponseEntity showByMarkValue(@PathVariable int markValue) {
        if (markValue < 1) {
            return new ResponseEntity("No mark found for mark value" + markValue, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(markService.getByMarkValue(markValue), HttpStatus.OK);
    }

    @GetMapping("mark/{markName:[A-Za-z]+}")
    public
    @ResponseBody
    Mark showByMarkValue(@PathVariable String markName) {
        return markService.getByMarkName(markName);
    }

    @GetMapping("studentsWithGivenMarkValue/{markValue}")
    public
    @ResponseBody
    ResponseEntity getStudentsByGivenMarkValue(@PathVariable int markValue) {
        try {
            Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkValue(markValue);
            Collections.sort(new ArrayList<>(studentsByGivenMarkValue));
            return new ResponseEntity(studentsByGivenMarkValue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("studentsWithGivenMarkName/{markName}")
    public
    @ResponseBody
    ResponseEntity getStudentsByGivenMarkName(@PathVariable String markName) {
        try {
            Set<Student> studentsByGivenMarkName = markService.getStudentsByGivenMarkName(markName);
            Collections.sort(new ArrayList<>(studentsByGivenMarkName));
            return new ResponseEntity(studentsByGivenMarkName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
