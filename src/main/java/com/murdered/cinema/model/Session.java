package com.murdered.cinema.model;

import java.sql.Time;

public class Session {
    private int id;
    private int sessionFilmId;
    private Time time;
    private double price;

    public Session() {}

    public Session(int id, int sessionFilmId, Time time, double price) {
        this.id = id;
        this.sessionFilmId = sessionFilmId;
        this.time = time;
        this.price = price;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}
