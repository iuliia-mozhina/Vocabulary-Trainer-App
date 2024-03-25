import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserNameTest {

    private static Nutzer testUser;

    @BeforeAll
    public static void setUp() {
        createDatabaseIfNotExists();
        testUser = new Nutzer("TestUser", null, null);
        GuestLoginStrategy.addToDatabase(testUser.getUserName());
    }

    private static void createDatabaseIfNotExists() {
        Path databasePath = Paths.get("userDatabase.txt");
        if (!Files.exists(databasePath)) {
            try {
                Files.createFile(databasePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(1)
    public void testSetUsername() {
        assertEquals("TestUser", testUser.getUserName(), "Initial username should match");

        testUser.setUsername("NewTestUser");

        assertEquals("NewTestUser", testUser.getUserName(), "Updated username should match");
    }

    @Test
    @Order(2)
    public void testSetUsernameEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> testUser.setUsername(""));
    }


    @Test
    @Order(3)
    public void testSetUsernameNull() {
        // Change the username to null
        assertThrows(IllegalArgumentException.class, () -> testUser.setUsername(null));
    }

    @Test
    @Order(4)
    public void OnlyCapital() {
        testUser.setUsername("NEWNAME");

        assertEquals("NEWNAME", testUser.getUserName(), "Updated username should match");
    }

    @Test
    @Order(5)
    public void NoCapital() {
        testUser.setUsername("newname");

        assertEquals("newname", testUser.getUserName(), "Updated username should match");
    }

    @AfterAll
    public static void cleanUp() {
        try {
            Files.delete(Paths.get("userDatabase.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
