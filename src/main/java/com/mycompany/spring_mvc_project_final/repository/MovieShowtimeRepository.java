package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.MovieShowtime;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface MovieShowtimeRepository extends CrudRepository <MovieShowtime,Long> {

}
