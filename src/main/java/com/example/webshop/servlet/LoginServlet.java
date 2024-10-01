package com.example.webshop.servlet;

import com.example.webshop.dao.UserDAO;
import com.example.webshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticateUser(username, password);

        if (user != null) {
            // Store user in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("products"); // Redirect to the products page after successful login
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
