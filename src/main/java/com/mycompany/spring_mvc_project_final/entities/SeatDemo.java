package com.mycompany.spring_mvc_project_final.entities;

public class SeatDemo {
    private int seatId;
    private String seatName;
    private String status;

    public SeatDemo(int seatId, String seatName, String status) {
        this.seatId = seatId;
        this.seatName = seatName;
        this.status = status;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
