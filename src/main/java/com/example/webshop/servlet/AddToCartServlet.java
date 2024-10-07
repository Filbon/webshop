package com.example.webshop.servlet;

import com.example.webshop.dao.ProductDAO;
import com.example.webshop.dto.CartDTO;
import com.example.webshop.dto.CartItemDTO;
import com.example.webshop.dto.ProductDTO;
import com.example.webshop.dto.UserDTO;
import com.example.webshop.handler.ProductHandler;
import com.example.webshop.handler.UserCartHandler;
import com.example.webshop.handler.UserHandler;
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
    private ProductHandler productHandler;
    private UserHandler userHandler;

    @Override
    public void init() {
        productHandler = new ProductHandler();
        userHandler = new UserHandler();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        CartDTO sessionCart = (CartDTO) session.getAttribute("cart");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        ProductDTO productDTO = productHandler.getProductById(productId);


        if (productDTO != null) {
            if (sessionCart == null) {
                sessionCart = new CartDTO();
                session.setAttribute("cart", sessionCart);
            }

            sessionCart.addItem(new CartItemDTO(productDTO, quantity));

            if (userDTO != null) {
                userHandler.addOrUpdateCartItem(userDTO.getId(), productId, quantity);
            }
        }

        response.sendRedirect("cart.jsp");
    }
}







