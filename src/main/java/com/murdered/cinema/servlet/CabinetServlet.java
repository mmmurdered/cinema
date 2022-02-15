package com.murdered.cinema.servlet;

import com.murdered.cinema.model.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminCabinet = "/WEB-INF/adminCabinet.jsp";
        String userCabinet = "/WEB-INF/userCabinet.jsp";
        String schedule = "/index.jsp";

        User user = (User) session.getAttribute("user");

        switch (user.getRole()){
            case REGISTERED_USER:
                request.getRequestDispatcher(userCabinet).forward(request, response);
                break;
            case ADMIN:
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
