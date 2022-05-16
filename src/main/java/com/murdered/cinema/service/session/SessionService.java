package com.murdered.cinema.service.session;

import com.murdered.cinema.dao.session.SessionDao;
import com.murdered.cinema.model.Session;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

public class SessionService {
    private static Logger logger = Logger.getLogger(SessionService.class);
    private final SessionDao sessionDao;

    public SessionService(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public void add(Session session) {
        logger.info("Adding new session");
        sessionDao.save(session);
    }

    public void delete(int id) {
        logger.info("Deleting session by id");
        sessionDao.delete(id);
    }

    public Session getSessionById(long id){
        logger.info("Getting session by id");
        return sessionDao.get(id);
    }

    public List<Session> getAllSessionsAfter(Timestamp time) {
        logger.info("Getting sessions after time");
        return sessionDao.getSessionAfterTime(time);
    }
}
