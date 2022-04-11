package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.dao.user.UserDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.service.user.UserService;
import com.murdered.cinema.util.EncryptionUtilMD5;
import com.murdered.cinema.util.MappingProperties;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String loginPage = mappingProperties.getProperty("loginPage");
        request.getRequestDispatcher(loginPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String errorPage = mappingProperties.getProperty("errorPage");
        String scheduleLink = mappingProperties.getProperty("scheduleLink");

        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();

        User user = null;
        UserService userService = new UserService(UserDaoImpl.getInstance());
        user = userService.getUserByLoginPass(login, EncryptionUtilMD5.getMd5(password));

        if(user.getLogin() == null){
            request.setAttribute("error", "User doesn`t exist");
            request.getRequestDispatcher(errorPage).forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("lang", request.getParameter("sessionLocale"));

            response.sendRedirect(request.getContextPath() + scheduleLink);
        }
    }
}
