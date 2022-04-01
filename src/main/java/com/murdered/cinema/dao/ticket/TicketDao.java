package com.murdered.cinema.dao.ticket;

import com.murdered.cinema.dao.DAO;
import com.murdered.cinema.model.Ticket;

import java.util.List;

public interface TicketDao extends DAO<Ticket> {
    public int getNumOfTicketsBySessionId(long sessionId);

    public List<Ticket> getTicketsByUserId(long userId);
}
