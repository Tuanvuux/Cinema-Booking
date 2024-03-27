package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room>findByCinema_CinemaId(Long cinemaId);
}
