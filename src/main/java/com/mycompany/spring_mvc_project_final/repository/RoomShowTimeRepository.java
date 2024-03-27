package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.entities.RoomShowtime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomShowTimeRepository extends CrudRepository<RoomShowtime, Long> {
    @Query(value = "select r.roomShowtimeId, r.roomId, r.showTimeId\n" +
            "from roomshowtime r join movieshowtime m on r.showTimeId= m.showTimeId join showtime s on s.showTimeId=r.showTimeId join movie mo on mo.movieId=m.movieId\n" +
            "where s.showDate = ?1 and mo.movieId=?2;",nativeQuery = true)
    List<RoomShowtime> findShowTimeByMovieIdAndDate(String showDate, Long movieId);
}
