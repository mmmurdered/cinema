package com.murdered.cinema.model;

import com.murdered.cinema.model.user.User;

public class Ticket {
    private int id;
    private int userId;
    private int sessionId;
    private int sessionFilmId;
    private int seatId;

    public Ticket(){}

    public Ticket(int id, int userId, int sessionId, int sessionFilmId, int seatId) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.sessionFilmId = sessionFilmId;
        this.seatId = seatId;
    }

    public Ticket(int userId, int sessionId, int sessionFilmId, int seatId) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.sessionFilmId = sessionFilmId;
        this.seatId = seatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionFilmId() {
        return sessionFilmId;
    }

    public void setSessionFilmId(int sessionFilmId) {
        this.sessionFilmId = sessionFilmId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", sessionId=" + sessionId +
                ", sessionFilmId=" + sessionFilmId +
                ", seatId=" + seatId +
                '}';
    }
}
