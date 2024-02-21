package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column
    private Date paymentDay;
    @Column
    private String paymentStatus;

    @Column
    private int amount;

    @Column
    private Long orderPaymentId;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany
    @JoinColumn(name = "ticketId")
    private List<Ticket> ticket;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getOrderPaymentId() {
        return orderPaymentId;
    }

    public void setOrderPaymentId(Long orderPaymentId) {
        this.orderPaymentId = orderPaymentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }
}
