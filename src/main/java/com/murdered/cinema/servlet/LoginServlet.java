package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.DBManager;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

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
        try {
            user = DBManager.getInstance().getUserByLoginAndPassword(login, password);
        } catch (SQLException e) {
            System.out.println("GET USER LOGIN PASS ERROR");
            e.printStackTrace();
        }

        System.out.println(user);

        /*if(user == null){
            String page = "/WEB-INF/login.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }*/

        request.getSession().setAttribute("user", user);

        if(user.getRole().equals(UserRole.ADMIN)){
            String page = "/WEB-INF/adminCabinet.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }
        if(user.getRole().equals(UserRole.REGISTERED_USER)){
            String page = "/WEB-INF/userCabinet.jsp";
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
