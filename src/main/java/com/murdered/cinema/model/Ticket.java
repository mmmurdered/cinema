package com.murdered.cinema.model;

import com.murdered.cinema.model.user.User;

public class Ticket {
    private int id;
    private User user;
    private Session session;
    private Film sessionFilm;
    private Seat seat;

    public Ticket(){}

    public Ticket(int id, User user, Session session, Film sessionFilm, Seat seat) {
        this.id = id;
        this.user = user;
        this.session = session;
        this.sessionFilm = sessionFilm;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Film getSessionFilm() {
        return sessionFilm;
    }

    public void setSessionFilm(Film sessionFilm) {
        this.sessionFilm = sessionFilm;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", session=" + session +
                ", sessionFilm=" + sessionFilm +
                ", seat=" + seat +
                '}';
    }
}
