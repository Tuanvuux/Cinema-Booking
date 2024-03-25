package com.mycompany.spring_mvc_project_final.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.mycompany.spring_mvc_project_final.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByShowDate(Date showDate);
}
