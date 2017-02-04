package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.services.showdataservices.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("mark")
    public
    @ResponseBody
    ResponseEntity addMark(
            @RequestParam String markName,
            @RequestParam int markValue) {
        try {
            Mark mark = new Mark();
            mark.setMarkName(markName);
            mark.setMarkValue(markValue);
            mark.setStudentsWithMark(Collections.emptySet());
            markService.saveMark(mark);
            return new ResponseEntity<>(mark, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("markNew")
    public @ResponseBody ResponseEntity createMark(@RequestBody Mark mark) {
        try {
            Mark savedMark = markService.saveMark(mark);
            return new ResponseEntity<>(savedMark, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("mark/{markValue}")
    public @ResponseBody ResponseEntity deleteMark(@PathVariable Integer markValue) {
        try {
            Mark mark = markService.getByMarkValue(markValue);
            markService.delete(mark);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("mark/{markId:\\d+}")
    public ResponseEntity updateMark(@PathVariable long markId, @RequestBody Mark mark) {
        try {
            Mark updatedMark = markService.updateMark(markId, mark);
            return new ResponseEntity(updatedMark, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("mark")
    public @ResponseBody ResponseEntity showAllMarks() {
        try {
            List<Mark> all = markService.getAll();
            return new ResponseEntity(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
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
    @ResponseBody ResponseEntity showByMarkName(@PathVariable String markName) {
        try {
            Mark byMarkName = markService.getByMarkName(markName);
            return new ResponseEntity(byMarkName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("studentsWithGivenMarkValue/{markValue}")
    public
    @ResponseBody
    ResponseEntity showStudentsByGivenMarkValue(@PathVariable int markValue) {
        try {
            Set<Student> studentsByGivenMarkValue = markService.getStudentsByGivenMarkValue(markValue);
            ArrayList<Student> students = new ArrayList<>(studentsByGivenMarkValue);
            Collections.sort(students);
            return new ResponseEntity(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("studentsWithGivenMarkName/{markName}")
    public
    @ResponseBody
    ResponseEntity showStudentsByGivenMarkName(@PathVariable String markName) {
        try {
            Set<Student> studentsByGivenMarkName = markService.getStudentsByGivenMarkName(markName);
            ArrayList<Student> students = new ArrayList<>(studentsByGivenMarkName);
            Collections.sort(students);
            return new ResponseEntity(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
