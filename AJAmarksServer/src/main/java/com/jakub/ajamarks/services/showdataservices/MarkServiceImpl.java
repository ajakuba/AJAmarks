package com.jakub.ajamarks.services.showdataservices;

import com.google.common.base.Preconditions;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.MarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ja on 15.01.17.
 */

@Service
@Transactional
public class MarkServiceImpl implements MarkService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MarkRepository markRepository;

    @Override
    public Mark saveMark(Mark mark) {
        Preconditions.checkArgument(mark != null, "Mark can not be null");
        logger.info("Mark: {}", mark);
        return markRepository.save(mark);
    }

    @Override
    public Mark updateMark(Mark mark) {
        Preconditions.checkArgument(mark != null, "Mark can not be null");
        Preconditions.checkNotNull(mark.getMarkValue(), "Mark value can't be null");
        Mark byMarkValueNamedQuery = markRepository.findByMarkValueNamedQuery(mark.getMarkValue());
        mark.setIdMark(byMarkValueNamedQuery.getIdMark());
        return markRepository.save(mark);

    }

    @Override
    public void delete(Mark mark) {
        Preconditions.checkArgument(mark != null, "Mark can not be null");
        markRepository.delete(mark);
    }

    @Override
    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    @Override
    public Mark getByMarkValue(int markValue) {
        Preconditions.checkArgument(markValue >= 1 && markValue <= 6, "Mark value must be from 1 to 6 value");
        return markRepository.findByMarkValueNamedQuery(markValue);
    }

    @Override
    public Mark getByMarkName(String markName) {
        Preconditions.checkArgument(markName != null, "Mark name can't be null");
        return markRepository.findByMarkNameNamedQuery(markName);
    }

    @Override
    public Set<Student> getStudentsByGivenMarkValue(int markValue) {
        Preconditions.checkArgument(markValue >= 1 && markValue <= 6, "Mark value must be from 1 to 6 value");
        Mark byMarkValueNamedQuery = markRepository.findByMarkValueNamedQuery(markValue);
        if (byMarkValueNamedQuery == null)
            throw new NullPointerException("No such a mark");
        else {
            Set<Student> studentSet = byMarkValueNamedQuery.getStudentsWithMark();
            if (studentSet.size() > 0)
                return studentSet;
            else
                return Collections.emptySet();
        }
    }

    @Override
    public Set<Student> getStudentsByGivenMarkName(String markName) {
        Preconditions.checkArgument(markName != null, "Mark name can't be null");
        Mark byMarkNameNamedQuery = markRepository.findByMarkNameNamedQuery(markName);
        if (byMarkNameNamedQuery == null)
            throw new NullPointerException("No such a mark");
        else {
            Set<Student> studentSet = byMarkNameNamedQuery.getStudentsWithMark();
            if (studentSet.size() > 0)
                return studentSet;
            else
                return Collections.emptySet();
        }
    }


}
