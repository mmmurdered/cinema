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

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    int countPage = 4;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String adminCabinet = mappingProperties.getProperty("adminCabinet");
        String userCabinet = mappingProperties.getProperty("userCabinet");
        String unregisteredSchedule = mappingProperties.getProperty("unregisteredSchedule");

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
                List<Session> sessionList = sessionService.getAllSessions();

                List<SessionDTO> sessionDTOList = new ArrayList<>();
                for (Session session_cinema : sessionList) {
                    SessionDTO sessionDTO = new SessionDTO(session_cinema);
                    sessionDTOList.add(sessionDTO);
                }

                if (filmList.size() > sessionList.size()) {
                    pagination(request, filmList, "filmListCinema");
                    pagination(request, sessionDTOList, "sessionListCinema");
                }
                pagination(request, sessionDTOList, "sessionListCinema");
                pagination(request, filmList, "filmListCinema");

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

        request.setAttribute("ticketDTOList", ticketDTOList);
    }


    private void pagination(HttpServletRequest request, List<?> list, String attribute) {
        if (list.size() % countPage == 0) {
            request.setAttribute("countPage", list.size() / countPage);
        } else {
            request.setAttribute("countPage", list.size() / countPage + 1);
        }

        request.setAttribute(attribute, list.stream().limit(countPage).collect(Collectors.toList()));

        if (request.getParameter("page") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            list = list.stream().skip((page - 1) * countPage).limit(countPage).collect(Collectors.toList());
            request.setAttribute(attribute, list);
        }
    }
}
