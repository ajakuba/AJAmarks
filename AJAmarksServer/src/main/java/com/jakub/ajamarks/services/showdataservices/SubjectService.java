package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Subject;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(Subject subject);
    Subject updateSubject(long subjectId, Subject subject);
    void deleteSubject(long subjectId);
    List<Subject> getAll();
    Subject getSubjectById(long subjectId);
}
