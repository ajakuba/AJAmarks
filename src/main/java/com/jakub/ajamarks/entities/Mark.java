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
public enum Mark {
    NDST(1), MRN(2), DST(3), DB(4), BDB(5), CEL(6);

    @Id
    @GeneratedValue
    private long mark_id;
    private int markValue;
    @ManyToMany
    private List<Student> studentList;

    Mark(){};

    Mark(int markValue){
        this.markValue=markValue;
    }

    public long getMark_id() {
        return mark_id;
    }

    public void setMark_id(long mark_id) {
        this.mark_id = mark_id;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
