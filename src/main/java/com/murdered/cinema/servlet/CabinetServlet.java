package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.session.SessionDaoImpl;
import com.murdered.cinema.dto.SessionDTO;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.service.session.SessionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminCabinet = "/WEB-INF/adminCabinet.jsp";
        String userCabinet = "/WEB-INF/userCabinet.jsp";
        String schedule = "/index.jsp";

        User user = (User) session.getAttribute("user");

        System.out.println(user.getRole());
        switch (user.getRole()){
            case REGISTERED_USER:
                request.getRequestDispatcher(userCabinet).forward(request, response);
                break;
            case ADMIN:
                SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
                List<Session> sessionList = sessionService.getAllSessions();

                List<SessionDTO> sessionDTOList = new ArrayList<>();
                for(Session session_cinema : sessionList){
                    SessionDTO sessionDTO = new SessionDTO(session_cinema);
                    sessionDTOList.add(sessionDTO);
                }
                request.setAttribute("sessionListCinema", sessionDTOList);

                System.out.println(request.getParameter("sessionListCinema"));

                request.getRequestDispatcher(adminCabinet).forward(request, response);
                break;
            case UNREGISTERED_USER:
                request.getRequestDispatcher(schedule).forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
