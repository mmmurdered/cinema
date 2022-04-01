package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.service.session.SessionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/editSession")
public class EditScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("addSession")) {
            request.getRequestDispatcher("/WEB-INF/addSession.jsp").forward(request, response);
        }
        if(action.equals("deleteSession")){
            deleteSession(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("action")) {
            case "addSession":
                addSession(request, response);
                break;
            case "deleteSession":
                deleteSession(request, response);
                break;
        }
    }

    private void addSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int filmId = Integer.parseInt(request.getParameter("film_id"));
        String date = getDateFromJsp(request);
        Timestamp dateTime = Timestamp.valueOf(date);

        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        int hours = Integer.parseInt(formatter.format(dateTime));

        if(hours < 9 || hours > 22){
            response.sendRedirect(request.getContextPath() + "/error");
            //TODO error page
        } else {
            double price = Double.parseDouble(request.getParameter("price"));
            int availablePlaces = Integer.parseInt(request.getParameter("places"));

            Session session = new Session();
            session.setSessionFilmId(filmId);
            session.setTime(dateTime);
            session.setPrice(price);
            session.setAvailablePlaces(availablePlaces);

            SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
            sessionService.add(session);

            response.sendRedirect(request.getContextPath() + "/cabinet");
        }
    }

    private String getDateFromJsp(HttpServletRequest request) {
        String date = request.getParameter("datetime");
        date = date.replace('T', ' ');
        date = date + ":00";

        return date;
    }

    private void deleteSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
        sessionService.delete(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect(request.getContextPath() + "/cabinet");
    }
}
