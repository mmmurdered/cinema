package com.murdered.cinema.dto;

import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.dao.user.UserDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.Ticket;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.service.session.SessionService;
import com.murdered.cinema.service.user.UserService;

public class TicketDTO {
    private int id;
    private User user;
    private Session session;
    private Film film;

    public TicketDTO(Ticket ticket) {
        UserService userService = new UserService(UserDaoImpl.getInstance());
        SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());

        this.id = ticket.getId();
        this.user = userService.getUserById(ticket.getUserId());
        this.session = sessionService.getSessionById(ticket.getSessionId());
        this.film = filmService.getFilmById(ticket.getSessionFilmId());
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
