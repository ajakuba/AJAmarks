package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ja on 11.01.17.
 */
public interface ClassRepository extends JpaRepository<Classroom, Long> {

    List<Student> findStudentsInClassroom();


}
