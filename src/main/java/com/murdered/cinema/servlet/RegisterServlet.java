package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.DBManager;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

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
            DBManager.getInstance().insertUser(newUser);
            //response.getWriter().println("Successfully registered");
            response.sendRedirect(request.getContextPath() + "/schedule");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
