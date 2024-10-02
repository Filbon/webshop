import com.example.webshop.dao.UserDAO;
import com.example.webshop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void testRegisterUser() {
        User user = new User(0, "testUser", "testPassword");
        assertTrue(userDAO.registerUser(user));

        User fetchedUser = userDAO.authenticateUser("testUser", "testPassword");
        assertNotNull(fetchedUser);
        assertEquals("testUser", fetchedUser.getUsername());
    }

    @Test
    void testAuthenticateUserWithValidCredentials() {
        User user = new User(0, "validUser", "validPassword");
        userDAO.registerUser(user);

        User authenticatedUser = userDAO.authenticateUser("validUser", "validPassword");
        assertNotNull(authenticatedUser);
        assertEquals("validUser", authenticatedUser.getUsername());
    }

    @Test
    void testAuthenticateUserWithInvalidCredentials() {
        assertNull(userDAO.authenticateUser("invalidUser", "invalidPassword"));
    }
}
