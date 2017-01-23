package com.jakub.ajamarks.entities;

import lombok.EqualsAndHashCode;
import org.springframework.expression.spel.CompilablePropertyAccessor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ja on 11.01.17.
 */

@EqualsAndHashCode(exclude = {"idStudent", "markList"})
@Entity
public class Student implements Comparable {

    @Id
    @GeneratedValue
    private long idStudent;
    @Column(unique = true, nullable = false)
    private String userName;
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn
    private Classroom classroom;
    @ManyToMany(mappedBy = "studentSet")
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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


    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                "name=" + lastName +
                "user=" + userName+

                '}';
    }

    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
        String firstStudent = this.getLastName().concat(" ").concat(this.getFirstName()).concat(" ").concat(this.getUserName());
        String secondStudent = student.getLastName().concat(" ").concat(student.getFirstName()).concat(" ").concat(student.getUserName());
        return firstStudent.compareTo(secondStudent);

    }
}
