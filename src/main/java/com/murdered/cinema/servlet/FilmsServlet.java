package com.murdered.cinema.servlet;

import com.murdered.cinema.model.user.User;
import com.murdered.cinema.util.MappingProperties;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/films")
public class FilmsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String unregisteredFilms = mappingProperties.getProperty("unregisteredFilms");
        String registeredFilms = mappingProperties.getProperty("registeredFilms");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        if(user == null){
            request.getRequestDispatcher(unregisteredFilms).forward(request, response);
        }

        request.getRequestDispatcher(registeredFilms).forward(request, response);
        //TODO FILMS JSTL
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
