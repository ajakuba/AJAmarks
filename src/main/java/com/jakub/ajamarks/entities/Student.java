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
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name = ("classroomId"))
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (userName != null ? !userName.equals(student.userName) : student.userName != null) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        return lastName != null ? lastName.equals(student.lastName) : student.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +

                '}';
    }
}
