package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface BookTicketRepository extends JpaRepository<BookTicket,Long> {
    @Query("SELECT bt FROM BookTicket bt WHERE bt.seatId = ?1 AND bt.status = ?2")
    Optional<BookTicket> findBySeatIdAndStatus(Long seatId, String status);
    @Query("SELECT bt.seatId FROM BookTicket bt WHERE bt.status = '1'")
    List<Long> findSelectedSeats();
    Optional<BookTicket> findBySeatId(Long seatId);
    Optional<BookTicket> findFirstBySeatIdAndRoomIdAndShowTimeId(Long seatId, Long roomId, Long showTimeId);
    List<BookTicket> findByUserId(Long userId);
//    @Modifying
//
//    @Query("DELETE FROM BookTicket bt WHERE bt.seatId = :seatId")
//    void deleteBySeatId(Long seatId);
}
