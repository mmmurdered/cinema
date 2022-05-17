package com.murdered.cinema.filter;

import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import com.murdered.cinema.util.MappingProperties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/editFilm/*", "/editSession/*"})
public class AccessFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        MappingProperties mappingProperties = MappingProperties.getInstance();
        String errorLink = mappingProperties.getProperty("errorLink");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User) session.getAttribute("user");

        if(user == null){
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        if(user.getRole() == UserRole.ADMIN){
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + errorLink);
        }
    }
}
