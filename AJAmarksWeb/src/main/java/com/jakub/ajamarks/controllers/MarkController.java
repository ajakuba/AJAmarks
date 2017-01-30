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

import java.util.List;
import java.util.Set;

/**
 * Created by ja on 30.01.17.
 */

@Controller
@RequestMapping("api/mark")
public class MarkController {

    @Autowired
    MarkService markService;
    @Autowired
    StudentService studentService;

    @PostMapping("addMark")
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

    @PostMapping("createMark")
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

    @DeleteMapping("deleteMark/{markValue}")
    public
    @ResponseBody
    void deleteMark(
            @PathVariable Integer markValue) {
        Mark mark = markService.getByMarkValue(markValue);
        markService.delete(mark);
    }

    @PutMapping("updateMark")
    public ResponseEntity updateMark(@RequestBody Mark mark) {
        try {
            Mark updatedMark = markService.updateMark(mark);
            return new ResponseEntity(updatedMark, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("showMarks")
    public
    @ResponseBody
    List<Mark> showAllMarks() {
        return markService.getAll();
    }


    @GetMapping("getBy/{markValue}")
    public
    @ResponseBody
    ResponseEntity showByMarkValue(@PathVariable int markValue) {
        if (markValue < 1) {
            return new ResponseEntity("No mark found for mark value" + markValue, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(markService.getByMarkValue(markValue), HttpStatus.OK);
    }

    @GetMapping("getBy/{markName}")
    public
    @ResponseBody
    Mark showByMarkValue(@PathVariable String markName) {
        return markService.getByMarkName(markName);
    }

    @GetMapping("getStudentsByGivenMarkValue/{markValue}")
    public
    @ResponseBody
    ResponseEntity getStudentsByGivenMarkValue(@PathVariable int markValue) {
        try {
            Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkValue(markValue);
            return new ResponseEntity(studentsByGivenMarkValue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getStudentsByGivenMarkName/{markName")
    public
    @ResponseBody
    ResponseEntity getStudentsByGivenMarkName(@PathVariable String markName) {
        try {
            Set<Student> studentsByGivenMarkName = markService.getStudentsByGivenMarkName(markName);
            return new ResponseEntity(studentsByGivenMarkName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
