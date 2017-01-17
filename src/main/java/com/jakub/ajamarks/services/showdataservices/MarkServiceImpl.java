package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ja on 15.01.17.
 */

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark saveMark(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public Mark updateByMarkValue(Mark mark) {
        Mark byMarkValueNamedQuery = markRepository.findByMarkValueNamedQuery(mark.getMarkValue());
        mark.setIdMark(byMarkValueNamedQuery.getIdMark());
        return markRepository.save(mark);

    }

    @Override
    public void delete(Mark mark) {
        markRepository.delete(mark);
    }

    @Override
    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    @Override
    public Mark getByMarkValue(int markValue) {
        return markRepository.findByMarkValueNamedQuery(markValue);
    }

    @Override
    public Mark getByMarkName(String markName) {
        return markRepository.findByMarkNameNamedQuery(markName);
    }

    @Override
    public Set<Student> getStudentsByGivenMarkValue(int markValue) {
        Mark byMarkValueNamedQuery = markRepository.findByMarkValueNamedQuery(markValue);
        if(byMarkValueNamedQuery==null)
            return Collections.emptySet();
        else {
            Set<Student> studentSet = byMarkValueNamedQuery.getStudentSet();
            if (studentSet.size() > 0)
                return studentSet;
            else
                return Collections.emptySet();
        }
    }

    @Override
    public Set<Student> getStudentsByGivenMarkName(String markName) {
        Mark byMarkNameNamedQuery = markRepository.findByMarkNameNamedQuery(markName);
        if (byMarkNameNamedQuery == null)
            return Collections.emptySet();
        else {
            Set<Student> studentSet = byMarkNameNamedQuery.getStudentSet();
            if (studentSet.size() > 0)
                return studentSet;
            else
                return Collections.emptySet();
        }
    }


}
