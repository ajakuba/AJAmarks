package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ja on 11.01.17.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //using @Praram adnotation
    //HQL (hibernate query language sytntax uses ":"
    @Query("select u from Student u where u.firstName = :firstName or u.lastName = :lastName")
    List<Student> findByLastnameOrFirstname(@Param("lastName") String lastname, @Param("firstName") String firstname);

    //native query
    @Query(value = "select * from Student where userName = ?1", nativeQuery = true)
    Student findByUserName(String userName);

    @Query("select u from Student u where u.firstName like %?1 and UPPER(u.firstName) = UPPER(?1)")
    List<Student> findByFirstNameStartsWithIgnoreCase(String firstName);

    @Query("select u from Student u where u.lastName like %?1 and UPPER(u.lastName) = UPPER(?1)")
    List<Student> findByLastNameStartsWithIgnoreCase(String firstName);

    @Query("select u from Student u order by u.lastName desc")
    List <Student> findAllOrderByLastNameDesc();

    @Query("select u from Student u where u.classroom = ?1")
    List<Student> findByClassroom(Classroom classroom);












}
