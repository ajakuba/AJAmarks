package com.jakub.ajamarks.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ja on 11.01.17.
 */

@EqualsAndHashCode(exclude = {"idClassroom", "studentsInClassroom"})
@ToString
@Getter
@Setter

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




}
