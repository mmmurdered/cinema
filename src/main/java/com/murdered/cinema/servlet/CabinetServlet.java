package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.dao.ticket.TicketDao;
import com.murdered.cinema.dao.ticket.TicketDaoImpl;
import com.murdered.cinema.dto.SessionDTO;
import com.murdered.cinema.dto.TicketDTO;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.Ticket;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.service.session.SessionService;
import com.murdered.cinema.service.ticket.TicketService;
import com.murdered.cinema.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(CabinetServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SEVLET: CABINET SERVLET DO GET");
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String adminCabinet = mappingProperties.getProperty("adminCabinet");
        String userCabinet = mappingProperties.getProperty("userCabinet");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        switch (user.getRole()) {
            case REGISTERED_USER:
                showTickets(request, user);

                request.getRequestDispatcher(userCabinet).forward(request, response);
                break;
            case ADMIN:
                FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
                List<Film> filmList = filmService.getAllFilms();

                SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
                List<Session> sessionList = sessionService.getAllSessionsAfter(new Timestamp(System.currentTimeMillis()));

                List<SessionDTO> sessionDTOList = SessionDTO.convertToSessionDTO(sessionList);

                pagination(request, filmList, sessionDTOList);

                request.getRequestDispatcher(adminCabinet).forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void showTickets(HttpServletRequest request, User user) {
        TicketService ticketService = new TicketService(TicketDaoImpl.getInstance());
        List<Ticket> ticketList = ticketService.getTicketsToUser(user.getId());

        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            TicketDTO ticketDTO = new TicketDTO(ticket);
            ticketDTOList.add(ticketDTO);
        }

        ticketDTOList = ticketDTOList.stream().filter(t -> t.getSession().getTime().after(Timestamp.from(Instant.now())))
                .collect(Collectors.toList());

        request.setAttribute("ticketDTOList", ticketDTOList);
    }


    private void pagination(HttpServletRequest request, List<?> firstList, List<?> secondList) {
        int countPage = 4;
        int size;
        if(firstList.size() > secondList.size()) size = firstList.size();
        else size = secondList.size();
        if (size % countPage == 0) {
            request.setAttribute("countPage", size / countPage);
        } else {
            request.setAttribute("countPage", size / countPage + 1);
        }
        request.setAttribute("filmListCinema", firstList.stream().limit(countPage).collect(Collectors.toList()));
        request.setAttribute("sessionListCinema", secondList.stream().limit(countPage).collect(Collectors.toList()));

        if (request.getParameter("page") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            firstList = firstList.stream().skip((page - 1) * countPage).limit(countPage).collect(Collectors.toList());
            secondList = secondList.stream().skip((page - 1) * countPage).limit(countPage).collect(Collectors.toList());
            request.setAttribute("filmListCinema", firstList);
            request.setAttribute("sessionListCinema", secondList);
        }
    }
}
