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
@ToString
@Getter
@Setter
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

}
