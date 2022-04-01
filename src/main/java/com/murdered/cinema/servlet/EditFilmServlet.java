package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.film.FilmDao;
import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.util.MappingProperties;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editFilm")
public class EditFilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String addFilm = mappingProperties.getProperty("addFilm");

        String action = request.getParameter("action");

        switch (action) {
            case "addFilm":
                request.getRequestDispatcher(addFilm).forward(request, response);
                break;
            case "deleteFilm":
                deleteFilm(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String cabinetLink = mappingProperties.getProperty("cabinetLink");
        addFilm(request, response);

        response.sendRedirect(request.getContextPath() + cabinetLink);
    }

    private void addFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filmTitle = request.getParameter("title");
        String filmGenre = request.getParameter("genre");
        String filmDescription = request.getParameter("description");
        int filmDuration = Integer.parseInt(request.getParameter("duration"));
        double filmIDMB = Double.parseDouble(request.getParameter("imdb"));

        Film newFilm = new Film(filmTitle, filmDescription, filmGenre, filmDuration, filmIDMB);

        System.out.println(newFilm);

        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
        filmService.add(newFilm);
    }

    private void deleteFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
        filmService.delete(Integer.parseInt(request.getParameter("id")));
    }

}
