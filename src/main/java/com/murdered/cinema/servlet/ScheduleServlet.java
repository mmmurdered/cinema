package com.murdered.cinema.servlet;

import com.murdered.cinema.model.user.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    public void init() {

    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String forUnregistered = "index.jsp";
        String forRegistered = "/WEB-INF/schedule.jsp";

        if(user == null){
            req.getRequestDispatcher(forUnregistered).forward(req, res);
        }

        req.getRequestDispatcher(forRegistered).forward(req, res);

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }

}