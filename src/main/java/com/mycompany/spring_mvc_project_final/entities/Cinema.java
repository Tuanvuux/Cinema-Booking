package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;


    @Column
    private String cinemaName;


    @Column
    private String cinemaAddress;


    @OneToMany(mappedBy = "cinema")
    private List<Room> room;


    public Long getCinemaId() {
        return cinemaId;
    }


    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }


    public String getCinemaName() {
        return cinemaName;
    }


    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }


    public String getCinemaAddress() {
        return cinemaAddress;
    }


    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }


    public List<Room> getRoom() {
        return room;
    }


    public void setRoom(List<Room> room) {
        this.room = room;
    }
}