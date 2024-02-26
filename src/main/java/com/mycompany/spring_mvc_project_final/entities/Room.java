package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;


    @Column
    private String roomName;


    @Column
    private int totalSeat;


    @ManyToOne
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;


    @OneToMany(mappedBy = "room")
    private List<Seat> seat;


    @OneToMany(mappedBy = "room")
    private List<RoomShowtime> roomShowtime;


    public Long getRoomId() {
        return roomId;
    }


    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public String getRoomName() {
        return roomName;
    }


    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


    public int getTotalSeat() {
        return totalSeat;
    }


    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }


    public Cinema getCinema() {
        return cinema;
    }


    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }


    public List<Seat> getSeat() {
        return seat;
    }


    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }


    public List<RoomShowtime> getRoomShowtime() {
        return roomShowtime;
    }


    public void setRoomShowtime(List<RoomShowtime> roomShowtime) {
        this.roomShowtime = roomShowtime;
    }
}
