package com.example.webshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private List<CartItemDTO> items;

    public CartDTO(List<CartItemDTO> items) {
        this.items = items;
    }

    public CartDTO() {
        this.items = new ArrayList<>();
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void addItem(CartItemDTO cartItem) {
        for (CartItemDTO item : items) {
            if (item.getProduct().getId() == cartItem.getProduct().getId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        items.add(cartItem);
    }
}