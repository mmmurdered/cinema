package com.murdered.cinema.servlet;

import com.murdered.cinema.dao.user.UserDaoImpl;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.service.user.UserService;
import com.murdered.cinema.util.EncryptionUtilMD5;
import com.murdered.cinema.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("SERVLET: REGISTER SERVLET DO GET");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String registerPage = mappingProperties.getProperty("registerPage");

        req.getRequestDispatcher(registerPage).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("SERVLET: REGISTER SERVLET DO POST");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String errorPage = mappingProperties.getProperty("errorPage");
        String scheduleLink = mappingProperties.getProperty("scheduleLink");

        String login = request.getParameter("login").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        System.out.println(login);

        User newUser = new User(login, email, EncryptionUtilMD5.getMd5(password));
        newUser.setRole(UserRole.REGISTERED_USER);

        UserService userService = new UserService(UserDaoImpl.getInstance());
        if (userService.getUserByLogin(login).getLogin() != null
                || userService.getUserByEmail(email).getEmail() != null) {
            request.setAttribute("error", "User already registered");
            request.getRequestDispatcher(errorPage).forward(request, response);
        } else {
            try {
                userService.addUser(newUser);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher(errorPage).forward(request, response);
            }
        }

        request.getSession().setAttribute("user", newUser);
        response.sendRedirect(request.getContextPath() + scheduleLink);
    }
}
