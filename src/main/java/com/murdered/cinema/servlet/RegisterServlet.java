package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.user.UserDaoImpl;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String page = "/WEB-INF/register.jsp";
        req.getRequestDispatcher(page).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(login, email, password);
        //System.out.println(newUser);
        newUser.setRole(UserRole.REGISTERED_USER);

        try {
            UserService userService = new UserService(UserDaoImpl.getInstance());
            userService.addUser(newUser);
            //response.getWriter().println("Successfully registered");
            response.sendRedirect(request.getContextPath() + "/schedule");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
