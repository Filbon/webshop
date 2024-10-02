package com.example.webshop.servlet;

import com.example.webshop.dao.UserCartDAO;
import com.example.webshop.dao.UserDAO;
import com.example.webshop.model.Cart;
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
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Load the user's cart from the database
            UserCartDAO userCartDAO = new UserCartDAO();
            Cart cart = userCartDAO.loadUserCart(user.getId());
            session.setAttribute("cart", cart); // Store the cart in session

            response.sendRedirect("products");
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
