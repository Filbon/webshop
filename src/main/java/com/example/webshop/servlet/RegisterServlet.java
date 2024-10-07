package com.example.webshop.servlet;

import com.example.webshop.dao.UserDAO;
import com.example.webshop.dto.UserDTO;
import com.example.webshop.handler.UserHandler;
import com.example.webshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserHandler userHandler = new UserHandler();
        UserDTO registeredUser = userHandler.registerUser(new UserDTO(0, username, password));

        if (registeredUser != null) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("registration.jsp?error=Registration Failed");
        }
    }
}
