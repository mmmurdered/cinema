package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.film.FilmDao;
import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.service.film.FilmService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editFilm")
public class EditFilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        System.out.println(request.getParameter("id"));

        switch (action) {
            case "addFilm":
                System.out.println("ADDFILM");
                request.getRequestDispatcher("/WEB-INF/addFilm.jsp").forward(request, response);
                break;
            case "deleteFilm":
                System.out.println("DELETEFILM");
                deleteFilm(request, response);
                break;
        }


        System.out.println("DOGET ADD");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addFilm(request, response);
        System.out.println("DOPOST EDIT FILM SERVLET");
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

        response.sendRedirect(request.getContextPath() + "/cabinet");
    }

    private void deleteFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
        //System.out.println(request.getParameter("id"));
        filmService.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/cabinet");
    }

}
