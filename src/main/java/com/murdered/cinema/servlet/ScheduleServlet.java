package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.session.SessionDao;
import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.dto.SessionDTO;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.service.session.SessionService;
import com.murdered.cinema.util.SessionSortUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String forRegistered = "/WEB-INF/schedule.jsp";
        String forUnregistered = "index.jsp";

        SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
        List<Session> sessionList = sessionService.getAllSessions(); //ORDERED BY TIME

        String sortType = req.getParameter("sort");
        if(sortType != null){
            switch (sortType){
                case "name":
                    sessionList = sessionService.getAllSessionsOrderName();
                    break;
                case "time":
                    sessionList = sessionService.getAllSessions();
                    break;
                case "available_places":
                    SessionSortUtil.SortByAvailablePlaces(sessionList);
                    break;
            }
        }

        List<SessionDTO> sessionDTOList = new ArrayList<>();
        for(Session session_cinema : sessionList){
            SessionDTO sessionDTO = new SessionDTO(session_cinema);
            sessionDTOList.add(sessionDTO);
        }

        req.setAttribute("sessionListCinema", sessionDTOList);

        if(user == null){
            req.getRequestDispatcher(forUnregistered).forward(req, res);
        } else {
            req.getRequestDispatcher(forRegistered).forward(req, res);
        }


    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }

}