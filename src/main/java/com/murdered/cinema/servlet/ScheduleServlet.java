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
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ScheduleServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        logger.info("SERVLET: SCHEDULE SERVLET DO GET");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String unregisteredSchedule = mappingProperties.getProperty("unregisteredSchedule");
        String registeredSchedule = mappingProperties.getProperty("registeredSchedule");

        SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
        List<Session> sessionList = sessionService.getAllSessionsAfter(new Timestamp(System.currentTimeMillis()));

        sessionList = filterIsAvailable(req, sessionList);
        sessionList = filterByDate(req, sessionList);

        List<SessionDTO> sessionDTOList = convertToDTOList(sessionList);
        sortSessionByUserPreference(req, sessionDTOList);

        if(!sessionDTOList.isEmpty()){
            Timestamp firstDate = sessionList.get(0).getTime();
            Timestamp lastDate = sessionList.get(sessionList.size() - 1).getTime();
            req.setAttribute("firstDate", timestampToHTMLValidTimePattern(firstDate));
            req.setAttribute("lastDate", timestampToHTMLValidTimePattern(lastDate));
        }
        req.setAttribute("sessionListCinema", sessionDTOList);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            req.getRequestDispatcher(unregisteredSchedule).forward(req, res);
        } else {
            req.getRequestDispatcher(registeredSchedule).forward(req, res);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }

    private void sortSessionByUserPreference(HttpServletRequest req, List<SessionDTO> sessionDTOList) {
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
    }

    private List<SessionDTO> convertToDTOList(List<Session> sessionList) {
        List<SessionDTO> sessionDTOList = new ArrayList<>();
        for(Session session_cinema : sessionList){
            SessionDTO sessionDTO = new SessionDTO(session_cinema);
            sessionDTOList.add(sessionDTO);
        }
        return sessionDTOList;
    }

    private List<Session> filterByDate(HttpServletRequest req, List<Session> sessionList) {
        String sessionDate = req.getParameter("date");
        if(sessionDate != null){
            if(sessionDate.length() != 0){
                LocalDate localDate = LocalDate.parse(sessionDate);
                Timestamp firstLimit = Timestamp.valueOf(localDate.atStartOfDay());
                localDate = localDate.plusDays(1);
                Timestamp secondLimit = Timestamp.valueOf(localDate.atStartOfDay());

                sessionList = sessionList.stream()
                        .filter(t -> t.getTime().after(firstLimit) && t.getTime().before(secondLimit))
                        .collect(Collectors.toList());
            }
        }
        return sessionList;
    }

    private List<Session> filterIsAvailable(HttpServletRequest req, List<Session> sessionList) {
        String isAvailable = req.getParameter("isAvailable");
        if(isAvailable != null) {
            sessionList = sessionList.stream().filter(t -> t.getAvailablePlaces() > 0).collect(Collectors.toList());
        }
        return sessionList;
    }

    private String timestampToHTMLValidTimePattern(Timestamp timestamp){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp.getTime()));
        return date;
    }


}