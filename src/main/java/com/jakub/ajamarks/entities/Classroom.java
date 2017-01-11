package com.jakub.ajamarks.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by ja on 11.01.17.
 */
@Entity
public class Classroom {

    @Id
    @GeneratedValue
    private long classRoom_Id;

    private int classroomNumber;
    private String classroomName;

    @OneToMany(mappedBy = "classroom")
    private Set<Student> studentsInClassroom;

    public long getClassRoom_Id() {
        return classRoom_Id;
    }

    public void setClassRoom_Id(long classRoom_Id) {
        this.classRoom_Id = classRoom_Id;
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
