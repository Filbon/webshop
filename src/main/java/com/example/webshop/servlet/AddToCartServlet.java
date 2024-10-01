package com.example.webshop.servlet;

import com.example.webshop.dao.ProductDAO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            // Retrieve the cart from the session
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new Cart(); // Create a new cart if it doesn't exist
                request.getSession().setAttribute("cart", cart);
            }
            // Add the new item to the cart
            cart.addItem(new CartItem(product, quantity));
        }

        response.sendRedirect("cart.jsp"); // Redirect to the cart page
    }
}

