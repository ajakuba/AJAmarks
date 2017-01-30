package com.jakub.ajamarks.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jakub.ajamarks.serializers.StudentSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(exclude = {"idStudent", "studentMarks", "classroom"})
@Getter
@Setter
@ToString
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
}
