package com.murdered.cinema.dao.session;

import com.murdered.cinema.dao.DAO;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.Ticket;

import java.sql.Timestamp;
import java.util.List;

public interface SessionDao extends DAO<Session> {

    public List<Session> getSessionAfterTime(Timestamp time);
}
