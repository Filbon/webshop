package com.example.webshop.handler;

import com.example.webshop.dao.UserCartDAO;
import com.example.webshop.dao.UserDAO;
import com.example.webshop.dto.UserCartDTO;
import com.example.webshop.dto.UserDTO;
import com.example.webshop.model.User;

public class UserHandler {
    private UserDAO userDAO;
    private UserCartDAO userCartDAO; // Add UserCartDAO

    public UserHandler() {
        this.userDAO = new UserDAO();
        this.userCartDAO = new UserCartDAO(); // Initialize UserCartDAO
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User(0, userDTO.getUsername(), userDTO.getPassword());
        if (userDAO.registerUser(user)) {
            return new UserDTO(user.getId(), user.getUsername(), user.getPassword());
        }
        return null;
    }

    public UserDTO authenticateUser(String username, String password) {
        User user = userDAO.authenticateUser(username, password);
        if (user != null) {
            return new UserDTO(user.getId(), user.getUsername(), user.getPassword());
        }
        return null;
    }

    public void addOrUpdateCartItem(int userId, int productId, int quantity) {
        UserCartDTO userCartItem = new UserCartDTO(userId, productId, quantity);
        userCartDAO.addOrUpdateCartItem(userId, productId, quantity); // This can remain as is
    }
}
