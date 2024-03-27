package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findByMovieId(Long movieId);
    List<Movie> findByMovieName(String movieName);
    List<Movie> findByDirector(String director);
    List<Movie> findByProducer(String producer);
    List<Movie> findByActor(String actor);
    List<Movie> findByMovieNameOrDirectorOrProducerOrActor(String movieName, String director, String producer, String actor);

    @Query(value = "SELECT DISTINCT m.movieId, m.actor, m.describeMovie, m.movieName, m.nation, m.photo, m.producer, m.trailer, m.cateId, m.director FROM movie m\n" +
            "join movieshowtime m1 on m.movieId = m1.movieId\n" +
            "join showtime s1 on m1.showTimeId= s1.showTimeId\n" +
            "join roomshowtime r1 on s1.showTimeId=r1.showTimeId\n" +
            "where s1.showDate = ?1",nativeQuery = true)
    List<Movie> findMovie(String showDate);
}