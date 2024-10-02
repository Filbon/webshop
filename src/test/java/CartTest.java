import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testAddItem() {
        Product product = new Product(1, "Sample Product", new BigDecimal("10.00"));
        cart.addItem(new CartItem(product, 1));

        assertEquals(1, cart.getItems().size());
        assertEquals(product.getName(), cart.getItems().get(0).getProduct().getName());
    }

    @Test
    void testRemoveItem() {
        Product product = new Product(1, "Sample Product", new BigDecimal("10.00"));
        cart.addItem(new CartItem(product, 1));
        cart.removeItem(product.getId());

        assertEquals(0, cart.getItems().size());
    }

    @Test
    public void testTotalPrice() {
        Cart cart = new Cart();
        Product product1 = new Product(1, "Product 1", new BigDecimal("20.00"));
        Product product2 = new Product(2, "Product 2", new BigDecimal("40.00"));

        cart.addItem(new CartItem(product1, 1));
        cart.addItem(new CartItem(product2, 1));

        BigDecimal expectedTotal = new BigDecimal("60.00");

        assertEquals(expectedTotal.setScale(2, BigDecimal.ROUND_HALF_UP),
                cart.getTotalPrice(),
                "Total price should be 60.00");
    }

}
