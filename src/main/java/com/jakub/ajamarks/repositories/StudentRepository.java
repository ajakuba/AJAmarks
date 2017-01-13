package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ja on 11.01.17.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Student findByUserName(String userName);
}
