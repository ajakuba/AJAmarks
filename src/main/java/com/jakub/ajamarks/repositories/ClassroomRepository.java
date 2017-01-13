package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ja on 11.01.17.
 */

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Classroom findByClassroomNumber(int number);
    Classroom findByClassroomName(String name);



}
