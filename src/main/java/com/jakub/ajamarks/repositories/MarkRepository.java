package com.jakub.ajamarks.repositories;

import com.jakub.ajamarks.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ja on 12.01.17.
 */
@Repository
public interface MarkRepository extends JpaRepository<Mark, Long>{

    Mark findByMarkValueNamedQuery(int markValue);
    Mark findByMarkNameNamedQuery(String markName);

}
