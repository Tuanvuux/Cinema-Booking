package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {

    @Query("SELECT s " +
            "FROM ShowTime s " +
            "INNER JOIN s.movieShowtime ms " +
            "WHERE ms.movie.movieId = :movieId AND s.showDate = :showDate")
    List<ShowTime> findShowTimesByMovieIdAndShowDate(@Param("movieId") Long movieId, @Param("showDate") Date showDate);}