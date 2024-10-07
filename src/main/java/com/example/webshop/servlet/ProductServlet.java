package com.example.webshop.servlet;

import com.example.webshop.dao.ProductDAO;
import com.example.webshop.dto.ProductDTO;
import com.example.webshop.handler.ProductHandler;
import com.example.webshop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private ProductHandler productHandler;

    @Override
    public void init() {
        productHandler = new ProductHandler();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDTO> productList = productHandler.getAllProducts();
        request.setAttribute("products", productList);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}
