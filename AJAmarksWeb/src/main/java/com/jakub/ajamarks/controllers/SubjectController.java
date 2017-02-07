package com.jakub.ajamarks.controllers;

import com.jakub.ajamarks.entities.Subject;
import com.jakub.ajamarks.services.showdataservices.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("subject")
    public Subject addSubject(@RequestParam String name){
        Subject subject = new Subject();
        subject.setSubjectName(name);
        return subjectService.saveSubject(subject);
    }

    @PostMapping("subjectNew")
    public Subject createSubject(@RequestBody Subject subject){
       return subjectService.saveSubject(subject);
    }

    @DeleteMapping("subject/{subjectId}")
    public void deleteSubject(@PathVariable long subjectId){
        try{
            subjectService.deleteSubject(subjectId);
            //return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            //return new ResponseEntity("No subject found for ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("subject/{subjectId}")
    public ResponseEntity updateSubject(@PathVariable long subjectId, @RequestBody Subject subject){
        try {
            Subject updatedSubject = subjectService.updateSubject(subjectId, subject);
            return new ResponseEntity(updatedSubject, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("subject")
    public List<Subject> showSubjects(){
        return subjectService.getAll();
    }

    @GetMapping("subject/{subjectId:\\d+}")
    public ResponseEntity showSubjectById(@PathVariable long subjectId){
        try {
            Subject subjectById = subjectService.getSubjectById(subjectId);
            return new ResponseEntity(subjectById, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


}
