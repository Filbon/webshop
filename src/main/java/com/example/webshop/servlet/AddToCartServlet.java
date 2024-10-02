package com.example.webshop.servlet;

import com.example.webshop.dao.ProductDAO;
import com.example.webshop.dao.UserCartDAO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.Product;
import com.example.webshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Cart sessionCart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        // Check if the product exists
        if (product != null) {
            // If session cart doesn't exist, create one
            if (sessionCart == null) {
                sessionCart = new Cart();
                session.setAttribute("cart", sessionCart);
            }
            // Add item to session cart
            sessionCart.addItem(new CartItem(product, quantity));

            // If user is logged in, also persist the cart item
            if (user != null) {
                UserCartDAO userCartDAO = new UserCartDAO();
                userCartDAO.addOrUpdateCartItem(user.getId(), productId, quantity);
            }
        }

        // Redirect to cart page
        response.sendRedirect("cart.jsp");
    }
}


