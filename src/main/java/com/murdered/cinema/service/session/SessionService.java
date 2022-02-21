package com.murdered.cinema.service.session;

import com.murdered.cinema.dao.session.SessionDao;
import com.murdered.cinema.model.Session;

import java.util.List;

public class SessionService {
    private final SessionDao sessionDao;

    public SessionService(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public List<Session> getAllSessions(){ //todo order by date
        return sessionDao.getAll();
    }

    public List<Session> getAllSessionsOrderPlaces() {
        return sessionDao.getSessionByAvailablePlaces();
    }

    public List<Session> getAllSessionsOrderName() {
        return sessionDao.getSessionByName();
    }

    public void add(Session session) {
        sessionDao.save(session);
    }

    public void delete(int id) {
        sessionDao.delete(id);
    }
}
