package com.murdered.cinema.service.ticket;

import com.murdered.cinema.dao.ticket.TicketDao;
import com.murdered.cinema.dao.user.UserDao;
import com.murdered.cinema.model.Ticket;
import org.apache.log4j.Logger;

import java.util.List;

public class TicketService {
    private static Logger logger = Logger.getLogger(TicketService.class);

    private final TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void addTicket(Ticket ticket){
        logger.info("Adding new ticket");
        ticketDao.save(ticket);
    }

    public int getNumOfTakenSeats(int id) {
        logger.info("Getting num of tickets");
        return ticketDao.getNumOfTicketsBySessionId(id);
    }

    public List<Ticket> getTicketsToUser(long id){
        logger.info("Getting tickets by user id");
        return ticketDao.getTicketsByUserId(id);
    }
}
