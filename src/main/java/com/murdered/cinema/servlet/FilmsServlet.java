package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/films")
public class FilmsServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(FilmsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: FILMS SEVLET DO GET");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String unregisteredFilms = mappingProperties.getProperty("unregisteredFilms");
        String registeredFilms = mappingProperties.getProperty("registeredFilms");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
        List<Film> filmList = filmService.getAllFilms();
        request.setAttribute("filmList", filmList);

        if(user == null){
            request.getRequestDispatcher(unregisteredFilms).forward(request, response);
        }

        request.getRequestDispatcher(registeredFilms).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
