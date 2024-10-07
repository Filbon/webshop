package com.example.webshop.handler;

import com.example.webshop.dao.UserCartDAO;
import com.example.webshop.dto.CartDTO;
import com.example.webshop.dto.CartItemDTO;
import com.example.webshop.dto.ProductDTO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class UserCartHandler {
    private UserCartDAO userCartDAO;
    private ProductHandler productHandler;

    public UserCartHandler() {
        this.userCartDAO = new UserCartDAO();
        this.productHandler = new ProductHandler();
    }

    public CartDTO loadUserCart(int userId) {
        Cart cart = userCartDAO.loadUserCart(userId);
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            cartItemDTOs.add(new CartItemDTO(new ProductDTO(product.getId(), product.getName(), product.getPrice()), item.getQuantity()));
        }

        return new CartDTO(cartItemDTOs);
    }

    public void addOrUpdateCartItem(int userId, int productId, int quantity) {
        // Logic to add or update cart item
        userCartDAO.addOrUpdateCartItem(userId, productId, quantity);
    }

    public void removeCartItem(int userId, int productId) {
        userCartDAO.removeCartItem(userId, productId);
    }
}
