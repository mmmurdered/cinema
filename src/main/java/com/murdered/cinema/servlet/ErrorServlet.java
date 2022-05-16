package com.murdered.cinema.servlet;

import com.murdered.cinema.service.film.FilmService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ErrorServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: ERROR SERVLET DO GET");
        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
