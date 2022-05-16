package com.murdered.cinema.service.ticket;

import com.murdered.cinema.dao.ticket.TicketDao;
import com.murdered.cinema.model.Ticket;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class TicketServiceTest {
    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void init(){
        ticketService = new TicketService(ticketDao);
    }

    @Test
    void addTicket() {
        Ticket ticket = new Ticket(1, 1, 1, 1);

        ticketService.addTicket(ticket);

        verify(ticketDao, times(1)).save(ticket);
    }

    @Test
    void getNumOfTakenSeats() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(1, 1, 1, 1));
        ticketList.add(new Ticket(2, 1, 1, 1));
        ticketList.add(new Ticket(3, 1, 1, 1));
        ticketList.add(new Ticket(4, 1, 1, 1));

        when(ticketDao.getNumOfTicketsBySessionId(ticketList.get(0).getSessionId())).thenReturn(ticketList.size());

        Assert.assertEquals(ticketList.size(), ticketService.getNumOfTakenSeats(ticketList.get(0).getSessionId()));
        verify(ticketDao, times(1)).getNumOfTicketsBySessionId(ticketList.get(0).getSessionId());
    }

    @Test
    void getTicketsToUser() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(1, 1, 1, 1));
        ticketList.add(new Ticket(2, 1, 1, 1));
        ticketList.add(new Ticket(3, 1, 1, 1));

        when(ticketDao.getTicketsByUserId(ticketList.get(0).getUserId())).thenReturn(ticketList);

        List<Ticket> result = ticketService.getTicketsToUser(ticketList.get(1).getUserId());

        Assert.assertEquals(ticketList, result);
        verify(ticketDao, times(1)).getTicketsByUserId(ticketList.get(1).getUserId());
    }
}