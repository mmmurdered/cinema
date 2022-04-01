package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.session.SessionDao;
import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.dto.SessionDTO;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.service.session.SessionService;
import com.murdered.cinema.util.MappingProperties;
import com.murdered.cinema.util.SessionSortUtil;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    int countPage = 5;
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String unregisteredSchedule = mappingProperties.getProperty("unregisteredSchedule");
        String registeredSchedule = mappingProperties.getProperty("registeredSchedule");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
        List<Session> sessionList = sessionService.getAllSessions(); //ORDERED BY TIME

        String isAvailable = req.getParameter("isAvailable");
        if(isAvailable != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            sessionList = sessionService.getAllSessionsAfter(now);
            sessionList = sessionList.stream().filter(t -> t.getAvailablePlaces() > 0).collect(Collectors.toList());
        }

        List<SessionDTO> sessionDTOList = new ArrayList<>();
        for(Session session_cinema : sessionList){
            SessionDTO sessionDTO = new SessionDTO(session_cinema);
            sessionDTOList.add(sessionDTO);
        }

        String sortType = req.getParameter("sort");
        if(sortType != null){
            switch (sortType){
                case "name":
                    SessionSortUtil.SortByName(sessionDTOList);
                    break;
                case "available_places":
                    SessionSortUtil.SortByAvailablePlaces(sessionDTOList);
                    break;
            }
        }

        req.setAttribute("sessionListCinema", sessionDTOList);

        if(user == null){
            req.getRequestDispatcher(unregisteredSchedule).forward(req, res);
        } else {
            req.getRequestDispatcher(registeredSchedule).forward(req, res);
        }

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }

    private void pagination(HttpServletRequest request, List<SessionDTO> sessionDTOList){
        if(sessionDTOList.size() % countPage == 0){
            request.setAttribute("countPage", sessionDTOList.size() / countPage);
        } else {
            request.setAttribute("countPage", sessionDTOList.size() / countPage + 1);
        }

        request.setAttribute("sessionListCinema", sessionDTOList.stream().limit(countPage).collect(Collectors.toList()));

        if(request.getParameter("page") != null){
            int page = Integer.parseInt(request.getParameter("page"));
            sessionDTOList = sessionDTOList.stream().skip((page - 1) * countPage).limit(countPage).collect(Collectors.toList());
            request.setAttribute("sessionListCinema", sessionDTOList);
        }
    }

}