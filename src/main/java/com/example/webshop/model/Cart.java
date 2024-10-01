package com.example.webshop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    // Add a CartItem to the cart
    public void addItem(CartItem cartItem) {
        // Check if the product already exists in the cart
        for (CartItem item : items) {
            if (item.getProduct().getId() == cartItem.getProduct().getId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return; // Product already in cart, update quantity
            }
        }
        items.add(cartItem); // Add new item if not already in cart
    }

    // Get all items in the cart
    public List<CartItem> getItems() {
        return items;
    }

    // Calculate total price of items in the cart
    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice().doubleValue() * item.getQuantity();
        }
        return total;
    }

    // Additional methods (e.g., removeItem, clearCart) can be added here
}

