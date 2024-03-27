package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.ShowTime;
import org.springframework.data.repository.CrudRepository;

public interface ShowTimeRepository extends CrudRepository<ShowTime, Long> {
    ShowTime findByShowTimeId(Long showTimeId);

}
