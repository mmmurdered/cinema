package com.murdered.cinema.dto;

import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.service.session.SessionService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionDTO {
    private int id;
    private Film sessionFilm;
    private Timestamp time;
    private double price;
    private int availablePlaces;

    public SessionDTO(Session session) {
        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());

        id = session.getId();
        price = session.getPrice();
        availablePlaces = session.getAvailablePlaces();
        time = session.getTime();
        sessionFilm = filmService.getFilmById(session.getSessionFilmId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getSessionFilm() {
        return sessionFilm;
    }

    public void setSessionFilm(Film sessionFilm) {
        this.sessionFilm = sessionFilm;
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

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public static List<SessionDTO> convertToSessionDTO(List<Session> sessionList) {
        List<SessionDTO> sessionDTOList = new ArrayList<>();
        for (Session session_cinema : sessionList) {
            SessionDTO sessionDTO = new SessionDTO(session_cinema);
            sessionDTOList.add(sessionDTO);
        }
        return sessionDTOList;
    }
}
