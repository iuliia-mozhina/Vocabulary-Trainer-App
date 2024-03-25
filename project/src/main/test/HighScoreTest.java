import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HighScoreTest {

    private static Nutzer testUser;

    @BeforeAll
    public static void setUp() {
        createDatabaseIfNotExists();
        testUser = new Nutzer("TestUser", null, null);
        GuestLoginStrategy.addToDatabase(testUser.getUserName());
        testUser.setHighScore();
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
    public void testCorrectUserName() {
        assertEquals("TestUser", testUser.getUserName(), "Usernames should match");
    }

    @Test
    @Order(2)
    public void testCorrectInitialHighScore() {
        assertEquals(0, Nutzer.getUserHighscore(testUser), "Scores should match");
    }

    @Test
    @Order(3)
    public void testUpdatedHighScore() {
        testUser.updateHighScore(2);
        assertEquals(2, Nutzer.getUserHighscore(testUser), "Scores should match");
    }

    @Test
    @Order(4)
    public void testValidSessionPoints() {
        testUser.resetHighScore();
        int validSessionPoints = PlayRound.getMaxQuestions();
        testUser.updateHighScore(validSessionPoints);
        assertEquals(validSessionPoints, Nutzer.getUserHighscore(testUser), "Scores should match");
    }

    @Test
    @Order(5)
    public void testInvalidSessionPoints() {
        testUser.resetHighScore();
        int maxQuestions = PlayRound.getMaxQuestions();
        int invalidSessionPoints = maxQuestions + 1;
        assertThrows(IllegalArgumentException.class, () -> testUser.updateHighScore(invalidSessionPoints));
    }

    @Test
    @Order(6)
    public void testNegativeSessionPoints() {
        assertThrows(IllegalArgumentException.class, () -> testUser.updateHighScore(-5));
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
