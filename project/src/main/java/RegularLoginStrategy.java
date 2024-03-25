import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code RegularLoginStrategy} class provides a concrete implementation for the regular login
 * strategy (for registered users).
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class RegularLoginStrategy implements LoginStrategy {
  private static final Logger LOGGER = Logger.getLogger(RegularLoginStrategy.class.getName());

  /** The path to the user database file. */
  private static final String USER_DATABASE_FILE = "./userDatabase.txt";

  /**
   * Attempts to authenticate a user based on the provided username and password.
   *
   * @param username The username entered by the user.
   * @param password The password entered by the user.
   * @return {@code true} if the login is successful, {@code false} otherwise.
   */
  @Override
  public boolean login(final String username, final String password) {
    try {
      final String storedPassword = readPasswordFromDatabase(username);
      if (storedPassword != null) {
        return storedPassword.equals(password);
      }
      return false;
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error during login", e);
      return false;
    }
  }

  /**
   * Reads the stored password from the user database for the specified username.
   *
   * @param targetUsername The username for which the password is to be retrieved.
   * @return The stored password associated with the username, or {@code null} if the username is
   *     not found.
   * @throws IOException If an error occurs while reading the user database.
   */
  private String readPasswordFromDatabase(final String targetUsername) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATABASE_FILE))) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String[] parts = line.split(",");
        if (parts.length >= 3 && parts[0].equals(targetUsername)) {
          return parts[2].trim();
        }
      }
      return null;
    }
  }
}
