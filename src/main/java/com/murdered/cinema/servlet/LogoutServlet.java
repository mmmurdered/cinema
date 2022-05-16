package com.murdered.cinema.servlet;

import com.murdered.cinema.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: LOGOUT SERVLET DO GET");

        MappingProperties mappingProperties = MappingProperties.getInstance();
        String unregisteredSchedule = mappingProperties.getProperty("unregisteredSchedule");

        //request.getSession().invalidate();
        request.getSession().setAttribute("user", null);
        request.getRequestDispatcher(unregisteredSchedule).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
