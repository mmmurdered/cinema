package com.murdered.cinema.service.ticket;

import com.murdered.cinema.dao.ticket.TicketDao;
import com.murdered.cinema.dao.user.UserDao;
import com.murdered.cinema.model.Ticket;

public class TicketService {
    private final TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void addTicket(Ticket ticket){
        ticketDao.save(ticket);
    }

    public int getNumOfTakenSeats(int id) {
        return ticketDao.getNumOfTicketsBySessionId(id);
    }
}
