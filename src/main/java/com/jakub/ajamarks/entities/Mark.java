package com.jakub.ajamarks.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by ja on 11.01.17.
 */

@Entity
public class Mark {

    @Id
    @GeneratedValue
    private long idMark;
    private int markValue;
    private String markName;
    @ManyToMany
    private List<Student> studentList;

    Mark(){};

    Mark(int markValue){
        if(markValue>6 || markValue<1)
            throw new IllegalArgumentException();
        this.markValue=markValue;
    }

    public long getIdMark() {
        return idMark;
    }

    public void setIdMark(long idMark) {
        this.idMark = idMark;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }


}
