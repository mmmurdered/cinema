package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.DBManager;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userRole = req.getParameter("role");
        if(userRole != null){
            String schedulePage = "/WEB-INF/index.jsp";
            req.getRequestDispatcher(schedulePage).forward(req, res);
        }

        String page = "/WEB-INF/login.jsp";
        req.getRequestDispatcher(page).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = DBManager.getInstance().getUserByLoginAndPassword(login, password); //TODO

        if(user == null){
            String page = "/WEB-INF/login.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }

        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("role", user.getRole());

        if(user.getRole().equals(UserRole.ADMIN)){
            String page = "/WEB-INF/adminCabinet.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }
        if(user.getRole().equals(UserRole.REGISTERED_USER)){
            String page = "/WEB-INF/userCabinet.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
