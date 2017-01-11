package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ja on 11.01.17.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentbyUserName(String userName);
}
