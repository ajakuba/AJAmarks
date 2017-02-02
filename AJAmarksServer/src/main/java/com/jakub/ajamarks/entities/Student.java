package com.jakub.ajamarks.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jakub.ajamarks.serializers.StudentSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(exclude = {"idStudent", "studentMarks", "classroom"})
@JsonSerialize(using = StudentSerializer.class)
@Entity

public class Student implements Comparable, Serializable {

    @Id
    @GeneratedValue
    private long idStudent;
    @Column(unique = true, nullable = false)
    private String userName;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Classroom classroom;
    @ManyToMany(mappedBy = "studentsWithMark", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mark> studentMarks;

    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
        String firstStudent = this.getLastName().concat(" ").concat(this.getFirstName()).concat(" ").concat(this.getUserName());
        String secondStudent = student.getLastName().concat(" ").concat(student.getFirstName()).concat(" ").concat(student.getUserName());
        return firstStudent.compareTo(secondStudent);
    }

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

    public List<Mark> getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(List<Mark> studentMarks) {
        this.studentMarks = studentMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", classroom=" + classroom +
                ", studentMarks=" + studentMarks +
                '}';
    }
}
