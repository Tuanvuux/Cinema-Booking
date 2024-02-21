package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatTypeId;


    @Column
    private String seatTypeName;


    @Column
    private int price;


    @OneToMany(mappedBy = "seatType")
    private List<Seat> seat;


    public Long getSeatTypeId() {
        return seatTypeId;
    }


    public void setSeatTypeId(Long seatTypeId) {
        this.seatTypeId = seatTypeId;
    }


    public String getSeatTypeName() {
        return seatTypeName;
    }


    public void setSeatTypeName(String seatTypeName) {
        this.seatTypeName = seatTypeName;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public List<Seat> getSeat() {
        return seat;
    }


    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }
}
