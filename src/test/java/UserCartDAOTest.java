import com.example.webshop.dao.UserCartDAO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserCartDAOTest {

    private UserCartDAO userCartDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize the database connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop_test", "root", "1234");
        userCartDAO = new UserCartDAO();

        // Create test data
        createTestData();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the test data
        cleanTestData();

        // Close the database connection
        if (connection != null) {
            connection.close();
        }
    }

    void createTestData() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Assuming user with id 1 and product with id 1 already exist, otherwise insert them
            statement.executeUpdate("INSERT INTO users (id, username, password) VALUES (1, 'testuser', 'password')");
            statement.executeUpdate("INSERT INTO products (id, name, price) VALUES (1, 'Test Product', 10.00)");
            statement.executeUpdate("INSERT INTO user_cart (user_id, product_id, quantity) VALUES (1, 1, 0)");
        }
    }

    void cleanTestData() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Delete test data
            statement.executeUpdate("DELETE FROM user_cart WHERE user_id = 1");
            statement.executeUpdate("DELETE FROM products WHERE id = 1");
            statement.executeUpdate("DELETE FROM users WHERE id = 1");
        }
    }

    @Test
    void testAddOrUpdateCartItem() {
        int userId = 1; // Existing user
        int productId = 1; // Existing product
        int quantity = 2;

        userCartDAO.addOrUpdateCartItem(userId, productId, quantity);
        Cart cart = userCartDAO.loadUserCart(userId);

        assertNotNull(cart);
        assertEquals(1, cart.getItems().size());
        assertEquals(quantity, cart.getItems().get(0).getQuantity());
    }

    @Test
    void testLoadUserCart() {
        int userId = 1; // Existing user
        Cart cart = userCartDAO.loadUserCart(userId);

        assertNotNull(cart);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void testRemoveCartItem() {
        int userId = 1; // Existing user
        int productId = 1; // Existing product

        userCartDAO.addOrUpdateCartItem(userId, productId, 2);
        userCartDAO.removeCartItem(userId, productId);

        Cart cart = userCartDAO.loadUserCart(userId);
        assertEquals(0, cart.getItems().size());
    }
}
