package com.jakub.ajamarks.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ja on 11.01.17.
 */

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long idStudent;
    @Column(unique = true, nullable = false)
    private String userName;
    private String first_name;
    private String last_name;
    @ManyToOne
    @JoinColumn(name = ("classroomId"))
    private Classroom classroom;
    @ManyToMany(mappedBy = "studentList")
    private List<Mark> markList;

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }
}
