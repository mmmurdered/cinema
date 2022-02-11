package com.murdered.cinema.servlet;

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
        String page = "index.jsp";
        req.getRequestDispatcher(page).forward(req, res);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }

}