package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Entity
@Table
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showTimeId;


    @Column
    private Date showDate;


    @Column
    private Time timeStart;


    @Column
    private Time timeEnd;


    @OneToMany(mappedBy = "showTime")
    private List<MovieShowtime> movieShowtime;


    @OneToMany(mappedBy = "showTime")
    private  List<RoomShowtime> roomShowtime;


    @OneToMany(mappedBy = "showTime")
    private  List<Ticket> ticket;


    public Long getShowTimeId() {
        return showTimeId;
    }


    public void setShowTimeId(Long showTimeId) {
        this.showTimeId = showTimeId;
    }


    public Date getShowDate() {
        return showDate;
    }


    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }


    public Time getTimeStart() {
        return timeStart;
    }


    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }


    public Time getTimeEnd() {
        return timeEnd;
    }


    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }


    public List<MovieShowtime> getMovieShowtime() {
        return movieShowtime;
    }


    public void setMovieShowtime(List<MovieShowtime> movieShowtime) {
        this.movieShowtime = movieShowtime;
    }


    public List<RoomShowtime> getRoomShowtime() {
        return roomShowtime;
    }


    public void setRoomShowtime(List<RoomShowtime> roomShowtime) {
        this.roomShowtime = roomShowtime;
    }


    public List<Ticket> getTicket() {
        return ticket;
    }


    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }
}
