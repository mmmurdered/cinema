package com.murdered.cinema.model;

import com.murdered.cinema.dao.ticket.TicketDaoImpl;
import com.murdered.cinema.service.ticket.TicketService;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Session {
    private int id;
    private int sessionFilmId;
    private Timestamp time;
    private double price;
    private int availablePlaces;

    public Session() {}

    public Session(int id, int sessionFilmId, Timestamp time, double price, int availablePlaces) {
        this.id = id;
        this.sessionFilmId = sessionFilmId;
        this.time = time;
        this.price = price;
        this.availablePlaces = availablePlaces;
    }

    public int getSessionFilmId() {
        return sessionFilmId;
    }

    public void setSessionFilmId(int sessionFilmId) {
        this.sessionFilmId = sessionFilmId;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
        calculateAvailablePlaces();

    }

    private void calculateAvailablePlaces(){
        TicketService ticketService = new TicketService(TicketDaoImpl.getInstance());
        this.availablePlaces = this.availablePlaces - ticketService.getNumOfTakenSeats(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionFilm() {
        return sessionFilmId;
    }

    public void setSessionFilm(int sessionFilmId) {
        this.sessionFilmId = sessionFilmId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionFilmId=" + sessionFilmId +
                ", time=" + time +
                ", price=" + price +
                ", availablePlaces=" + availablePlaces +
                '}';
    }
}
