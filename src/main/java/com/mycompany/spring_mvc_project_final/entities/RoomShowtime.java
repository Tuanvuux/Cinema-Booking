package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;


@Entity
@Table
public class RoomShowtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomShowtimeId;


    @ManyToOne
    @JoinColumn(name = "showTimeId")
    private ShowTime showTime;


    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;


    public Long getRoomShowtimeId() {
        return roomShowtimeId;
    }


    public void setRoomShowtimeId(Long roomShowtimeId) {
        this.roomShowtimeId = roomShowtimeId;
    }


    public ShowTime getShowTime() {
        return showTime;
    }


    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }


    public Room getRoom() {
        return room;
    }


    public void setRoom(Room room) {
        this.room = room;
    }
}
