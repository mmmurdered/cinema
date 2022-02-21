package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.dao.user.UserDaoImpl;
import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.service.film.FilmService;
import com.murdered.cinema.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /*HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");

        if(user.getRole() != null){
            String schedulePage = "/WEB-INF/index.jsp";
            req.getRequestDispatcher(schedulePage).forward(req, res);
        }
*/
        String page = "/WEB-INF/login.jsp";
        req.getRequestDispatcher(page).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        System.out.println(login);
        System.out.println(password);

        User user = null; //TODO

        UserService userService = new UserService(UserDaoImpl.getInstance());
        user = userService.getUser(login, password);

        FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
        List<Film> filmList = filmService.getAllFilms();

        request.setAttribute("filmList", filmList);

        System.out.println(user);

        /*if(user == null){
            String page = "/WEB-INF/login.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }*/

        request.getSession().setAttribute("user", user);

        String page = "/WEB-INF/schedule.jsp";
        response.sendRedirect(request.getContextPath() + "/schedule");

    }
}
