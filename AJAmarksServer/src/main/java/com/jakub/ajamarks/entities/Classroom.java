package com.jakub.ajamarks.entities;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jakub.ajamarks.serializers.ClassroomSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(exclude = {"idClassroom", "studentsInClassroom"})
@JsonSerialize(using = ClassroomSerializer.class)
@Entity
public class Classroom implements Serializable {

    @Id
    @GeneratedValue
    private long idClassroom;
    @Column(unique = true)
    private int classroomNumber;
    @Column(unique = true)
    private String classroomName;
    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER)
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

    @Override
    public String toString() {
        return "Classroom{" +
                "idClassroom=" + idClassroom +
                ", classroomNumber=" + classroomNumber +
                ", classroomName='" + classroomName + '\'' +
                '}';
    }
}
