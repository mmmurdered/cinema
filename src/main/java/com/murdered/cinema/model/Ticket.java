package com.murdered.cinema.model;

import com.murdered.cinema.model.user.User;

public class Ticket {
    private int id;
    private int userId;
    private int sessionId;
    private int sessionFilmId;

    public Ticket(){}

    public Ticket(int id, int userId, int sessionId, int sessionFilmId) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.sessionFilmId = sessionFilmId;
    }

    public Ticket(int userId, int sessionId, int sessionFilmId) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.sessionFilmId = sessionFilmId;
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


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", sessionId=" + sessionId +
                ", sessionFilmId=" + sessionFilmId +
                '}';
    }
}
