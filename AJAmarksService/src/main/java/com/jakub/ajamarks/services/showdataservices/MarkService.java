package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by ja on 15.01.17.
 */
public interface MarkService {

    Mark saveMark(Mark mark);
    Mark updateByMarkValue(Mark mark);
    void delete(Mark mark1);
    List<Mark> getAll();
    Mark getByMarkValue(int markValue);
    Mark getByMarkName(String markName);
    Set<Student> getStudentsByGivenMarkValue(int markValue);
    Set<Student> getStudentsByGivenMarkName(String markName);
}
