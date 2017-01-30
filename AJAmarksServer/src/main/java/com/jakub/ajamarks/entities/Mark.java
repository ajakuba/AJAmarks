package com.jakub.ajamarks.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jakub.ajamarks.serializers.MarkSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(exclude = {"idMark", "studentsWithMark"})
@ToString
@Getter
@Setter
@JsonSerialize(using = MarkSerializer.class)
@Entity
//string: "findByMarkValueNamedQuery" mas be the same as method name in MarkRepository, the same for MarkName
@NamedQueries({
        @NamedQuery(name = "Mark.findByMarkValueNamedQuery", query = "select m from Mark m where m.markValue = ?1"),
        @NamedQuery(name = "Mark.findByMarkNameNamedQuery", query = "select m from Mark m where m.markName = ?1")
        })
public class Mark implements Comparable, Serializable{

    @Id
    @GeneratedValue
    private long idMark;
    @Column(unique = true)
    private int markValue;
    @Column(unique = true)
    private String markName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> studentsWithMark;

    @Override
    public int compareTo(Object o) {
        Mark o1 = (Mark) o;
        return this.getMarkValue()-o1.getMarkValue();
    }
}
