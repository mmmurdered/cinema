package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.service.session.SessionService;
import com.murdered.cinema.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/editSession")
public class EditScheduleServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditFilmServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: EDIT SCHEDULE SERVLET DO GET");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String addSession = mappingProperties.getProperty("addSession");

        String action = request.getParameter("action");

        if (action.equals("addSession")) {
            request.getRequestDispatcher(addSession).forward(request, response);
        }
        if(action.equals("deleteSession")){
            deleteSession(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: EDIT SCHEDULE SERVLET DO POST");

        switch (request.getParameter("action")) {
            case "addSession":
                addSession(request, response);
                break;
            case "deleteSession":
                deleteSession(request, response);
                break;
        }
    }

    private void addSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String cabinetLink = mappingProperties.getProperty("cabinetLink");
        String errorPage = mappingProperties.getProperty("errorPage");

        int filmId = Integer.parseInt(request.getParameter("film_id"));
        String date = getDateFromJsp(request);
        Timestamp dateTime = Timestamp.valueOf(date);

        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        int hours = Integer.parseInt(formatter.format(dateTime));

        if(hours < 9 || hours > 22){
            request.setAttribute("error", "Time must be after 9 and after 22");
            request.getRequestDispatcher(errorPage).forward(request, response);
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

            response.sendRedirect(request.getContextPath() + cabinetLink);
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
