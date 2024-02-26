package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foodOrder")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodOrderId;

    @Column
    private int quantity;

    @OneToMany(mappedBy = "foodOrder")
    private List<Food> food;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    public Long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(Long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
