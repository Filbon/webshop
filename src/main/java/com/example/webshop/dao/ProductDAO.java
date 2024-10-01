package com.example.webshop.dao;

import com.example.webshop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "1ReallyD01");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            // Corrected query to select all columns
            String query = "SELECT * FROM products";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Ensure the column names match your database schema
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getBigDecimal("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public Product getProductById(int productId) {
        Product product = null;
        try {
            String query = "SELECT * FROM products WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getBigDecimal("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


}