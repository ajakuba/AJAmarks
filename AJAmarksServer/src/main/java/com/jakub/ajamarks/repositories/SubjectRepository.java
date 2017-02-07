package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ja on 06.02.17.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
}
