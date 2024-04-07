package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.entities.RoomShowtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomShowTimeRepository extends JpaRepository<RoomShowtime, Long> {
    List<RoomShowtime> findByShowTime_ShowTimeIdIn(List<Long> showTimeIds);
}