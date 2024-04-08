package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class BookTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookTicketId;

    @Column
    private Long cinemaId;

    @Column
    private LocalDateTime lastSelectedTime;

    @Column
    private Long roomId;

    @Column
    private Long seatId;

    @Column
    private Long showTimeId;

    @Column
    private String status;

    @Column
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookTicketId() {
        return bookTicketId;
    }

    public void setBookTicketId(Long bookTicketId) {
        this.bookTicketId = bookTicketId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public LocalDateTime getLastSelectedTime() {
        return lastSelectedTime;
    }

    public void setLastSelectedTime(LocalDateTime lastSelectedTime) {
        this.lastSelectedTime = lastSelectedTime;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(Long showTimeId) {
        this.showTimeId = showTimeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
