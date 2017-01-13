package com.jakub.ajamarks.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ja on 11.01.17.
 */
@Entity
public class Classroom {

    @Id
    @GeneratedValue
    private long idClassroom;
    @Column(unique = true)
    private int classroomNumber;
    @Column(unique = true)
    private String classroomName;

    @OneToMany(mappedBy = "classroom")
    private Set<Student> studentsInClassroom;

    public long getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(long idClassroom) {
        this.idClassroom = idClassroom;
    }

    public int getClassroomNumber() {
        return classroomNumber;
    }

    public void setClassroomNumber(int classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Set<Student> getStudentsInClassroom() {
        return studentsInClassroom;
    }

    public void setStudentsInClassroom(Set<Student> studentsInClassroom) {
        this.studentsInClassroom = studentsInClassroom;
    }
}
