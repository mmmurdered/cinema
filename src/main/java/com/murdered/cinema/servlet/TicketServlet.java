package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.session.SessionDao;
import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.dao.ticket.TicketDaoImpl;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.Ticket;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.service.session.SessionService;
import com.murdered.cinema.service.ticket.TicketService;
import com.murdered.cinema.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet("/buy")
public class TicketServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ScheduleServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: TICKET SERVLET DO GET");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String errorPage = mappingProperties.getProperty("errorPage");
        String buyTicketPage = mappingProperties.getProperty("buyTicketPage");
        User sessionUser = (User) request.getSession().getAttribute("user");

        if(sessionUser.getRole().equals(UserRole.ADMIN)){
            request.setAttribute("error", "You are on admin page, not users. Buy only from user page");
            request.getRequestDispatcher(errorPage).forward(request, response);
        }

        request.setAttribute("id", request.getParameter("session_id"));
        request.setAttribute("film_id", request.getParameter("session_film_id"));

        SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
        Session session = sessionService.getSessionById(Integer.parseInt(request.getParameter("session_id")));
        int availablePlaces = session.getAvailablePlaces();

        request.setAttribute("available_places", availablePlaces);

        request.getRequestDispatcher(buyTicketPage).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: TICKET SERVLET DO POST");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String cabinetLink = mappingProperties.getProperty("cabinetLink");

        User sessionUser = (User) request.getSession().getAttribute("user");

        int numOfSeats = Integer.parseInt(request.getParameter("numOfTickets"));
        int userId = sessionUser.getId();
        int sessionId = Integer.parseInt(request.getParameter("session_id"));
        int sessionFilmId = Integer.parseInt(request.getParameter("session_film_id"));

        TicketService ticketService = new TicketService(TicketDaoImpl.getInstance());
        for (int i = 0; i < numOfSeats; i++) {
            Ticket ticket = new Ticket();

            ticket.setUserId(userId);
            ticket.setSessionFilmId(sessionFilmId);
            ticket.setSessionId(sessionId);

            ticketService.addTicket(ticket);
        }

        response.sendRedirect(request.getContextPath() + cabinetLink);
    }
}
