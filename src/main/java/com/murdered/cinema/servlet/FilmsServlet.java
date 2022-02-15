package com.murdered.cinema.servlet;

import com.murdered.cinema.model.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/films")
public class FilmsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String forUnregistered = "/WEB-INF/unregisteredFilms.jsp";
        String forRegistered = "/WEB-INF/registeredFilms.jsp";

        if(user == null){
            request.getRequestDispatcher(forUnregistered).forward(request, response);
        }

        request.getRequestDispatcher(forRegistered).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
