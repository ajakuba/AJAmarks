package com.jakub.ajamarks.services.showdataservices;

import com.google.common.base.Preconditions;
import com.jakub.ajamarks.entities.Subject;
import com.jakub.ajamarks.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject saveSubject(Subject subject) {
        Preconditions.checkArgument(subject!=null, "subject can't be null");
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(long subjectId, Subject subject) {
        Subject foundSubject = subjectRepository.findOne(subjectId);
        subject.setSubjectId(foundSubject.getSubjectId());
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(long subjectId) {
        Preconditions.checkArgument(subjectId>0, "id can't be less than 1");
            subjectRepository.delete(subjectId);
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(long subjectId) {
        Preconditions.checkArgument(subjectId>0, "id can't be less than 1");
        Subject one = subjectRepository.findOne(subjectId);
        System.out.println(one);
        return one;
    }
}
