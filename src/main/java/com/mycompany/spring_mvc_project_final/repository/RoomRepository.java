package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.Room;
import com.mycompany.spring_mvc_project_final.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room>findByCinema_CinemaId(Long cinemaId);
//    @Query(value = "SELECT r.roomId, r.roomName, r.totalSeat " +
//            "FROM Room r " +
//            "INNER JOIN ShowTime s ON m.showTime.showTimeId = s.showTimeId " +
//            "WHERE m.movie.movieId = :movieId AND s.showDate = :showDate")
//    List<Room> findRoomsByMovieIdAndShowDate(Long movieId, String showDate);
//

}
