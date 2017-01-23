package com.jakub.ajamarks.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ja on 11.01.17.
 */

@EqualsAndHashCode(exclude = {"idMark", "studentSet"})
@ToString
@Entity

//string: "findByMarkValueNamedQuery" mas be the same as method name in MarkRepository, the same for MarkName
@NamedQueries({
        @NamedQuery(name = "Mark.findByMarkValueNamedQuery", query = "select m from Mark m where m.markValue = ?1"),
        @NamedQuery(name = "Mark.findByMarkNameNamedQuery", query = "select m from Mark m where m.markName = ?1")
        })

public class Mark implements Comparable{

    @Id
    @GeneratedValue
    private long idMark;
    private int markValue;
    private String markName;
    @ManyToMany
    private Set<Student> studentSet;

    public Mark() {
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

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }


    @Override
    public int compareTo(Object o) {
        Mark o1 = (Mark) o;
        return this.getMarkValue()-o1.getMarkValue();
    }
}
