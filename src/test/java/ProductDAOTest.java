import com.example.webshop.dao.ProductDAO;
import com.example.webshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    private ProductDAO productDAO;

    @BeforeEach
    void setUp() {
        productDAO = new ProductDAO();
        setupTestDatabase(); // Set up test data in the database before each test
    }

    // Method to set up test data in the database
    private void setupTestDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop_test", "root", "1234");
             Statement statement = connection.createStatement()) {

            // Clean up the table before inserting new data
            statement.executeUpdate("DELETE FROM products");

            // Insert test products
            statement.executeUpdate("INSERT INTO products (name, price) VALUES ('Test Product A', 10.00)");
            statement.executeUpdate("INSERT INTO products (name, price) VALUES ('Test Product B', 20.00)");
            statement.executeUpdate("INSERT INTO products (name, price) VALUES ('Test Product C', 30.00)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productDAO.getAllProducts();

        // Validate that the correct number of products is returned
        assertEquals(3, products.size());

        // Validate the contents of the first product
        Product firstProduct = products.get(0);
        assertEquals("Test Product A", firstProduct.getName());
        assertEquals(new BigDecimal("10.00"), firstProduct.getPrice());
    }

    @Test
    void testGetProductById() {
        List<Product> products = productDAO.getAllProducts();
        assertFalse(products.isEmpty(), "Product list should not be empty.");
        Product product = products.get(0);

        assertNotNull(product);
        assertEquals("Test Product A", product.getName());
        assertEquals(new BigDecimal("10.00"), product.getPrice());
    }

    @Test
    void testGetProductById_NonExistent() {
        Product product = productDAO.getProductById(999); // Non-existent ID

        assertNull(product);
    }
}
