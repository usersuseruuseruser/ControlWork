package com.solncev.net.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password123";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (LOGIN.equalsIgnoreCase(login) && PASSWORD.equals(password)) {
            // session
            LOGGER.info("User with login {} logged in", login);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            // cookie
            Cookie cookie = new Cookie("username", login);
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);

            resp.sendRedirect("main.jsp");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
