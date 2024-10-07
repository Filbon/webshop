package com.example.webshop.dao;

import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.Product;
import com.example.webshop.model.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCartDAO {
    private Connection connection;

    public UserCartDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cart loadUserCart(int userId) {
        Cart cart = new Cart();
        String query = "SELECT uc.product_id, uc.quantity, p.name, p.price " +
                "FROM user_cart uc " +
                "JOIN products p ON uc.product_id = p.id " +
                "WHERE uc.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");

                Product product = new Product(productId, name, price);
                CartItem cartItem = new CartItem(product, quantity);
                cart.addItem(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }


    public void removeCartItem(int userId, int productId) {
        String query = "DELETE FROM user_cart WHERE user_id = ? AND product_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdateCartItem(int userId, int productId, int quantity) {
        String query = "INSERT INTO user_cart (user_id, product_id, quantity) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE quantity = quantity + ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

