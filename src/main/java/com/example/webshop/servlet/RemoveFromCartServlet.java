package com.example.webshop.servlet;

import com.example.webshop.dao.UserCartDAO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart != null && user != null) {
            cart.removeItem(productId);
            session.setAttribute("cart", cart);

            UserCartDAO userCartDAO = new UserCartDAO();
            userCartDAO.removeCartItem(user.getId(), productId);
        }

        response.sendRedirect("cart.jsp");
    }
}



