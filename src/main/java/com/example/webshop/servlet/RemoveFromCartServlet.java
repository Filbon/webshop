package com.example.webshop.servlet;

import com.example.webshop.dto.CartDTO;
import com.example.webshop.dto.UserDTO;
import com.example.webshop.handler.UserCartHandler;
import com.example.webshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    private UserCartHandler userCartHandler;

    @Override
    public void init() {
        userCartHandler = new UserCartHandler();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        if (cartDTO != null) {
            cartDTO.removeItem(productId); // Remove item using DTO
            session.setAttribute("cart", cartDTO);
        }

        if (userDTO != null) {
            userCartHandler.removeCartItem(userDTO.getId(), productId);
        }

        response.sendRedirect("cart.jsp");
    }
}






