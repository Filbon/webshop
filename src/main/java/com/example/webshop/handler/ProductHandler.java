package com.example.webshop.handler;

import com.example.webshop.dao.ProductDAO;
import com.example.webshop.dto.ProductDTO;
import com.example.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler {
    private ProductDAO productDAO;

    public ProductHandler() {
        this.productDAO = new ProductDAO();
    }

    public ProductDTO getProductById(int productId) {
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            return new ProductDTO(product.getId(), product.getName(), product.getPrice());
        }
        return null;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(new ProductDTO(product.getId(), product.getName(), product.getPrice()));
        }
        return productDTOs;
    }
}
