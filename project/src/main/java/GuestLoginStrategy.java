import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code GuestLoginStrategy} class represents a concrete implementation for the guest login
 * strategy. It provides methods for logging in a user as a guest.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class GuestLoginStrategy implements LoginStrategy {
  /** The path to the user database file. */
  private static final String DATABASE_FILE = "./userDatabase.txt";

  /**
   * Attempts to log in a user as a guest.
   *
   * @param username the username to be logged in
   * @param password not used for guest login
   * @return {@code true} if the login is successful, {@code false} otherwise
   */
  @Override
  public boolean login(final String username, final String password) {
    // Check if the username is in the "database" file
    if (isUsernameInDatabase(username)) {
      Logger.getLogger(GuestLoginStrategy.class.getName())
          .log(Level.INFO, "Guest login for username: " + username + " successful");
    } else {
      // If the username is not in the database, add it with an initialized high score of 0
      addToDatabase(username);
      Logger.getLogger(GuestLoginStrategy.class.getName())
          .log(Level.INFO, "Guest login for username: " + username + " successful (new user)");
    }
    return true;
  }

  /**
   * Checks if the provided username is in the "database" file.
   *
   * @param username the username to be checked
   * @return {@code true} if the username is found in the database, {@code false} otherwise
   */
  private boolean isUsernameInDatabase(final String username) {
    try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String[] parts = line.split(",");
        if (parts.length > 0 && parts[0].equals(username)) {
          return true;
        }
      }
    } catch (IOException e) {
      Logger.getLogger(GuestLoginStrategy.class.getName())
          .log(Level.SEVERE, "Error reading userDatabase.txt", e);
    }
    return false;
  }

  /**
   * Adds a new username with an initialized high score to the "database" file.
   *
   * @param username the username to be added to the database
   */
  public static void addToDatabase(final String username) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_FILE, true))) {
      writer.println(username + "," + 0);
    } catch (IOException e) {
      Logger.getLogger(GuestLoginStrategy.class.getName())
          .log(Level.SEVERE, "Error writing to userDatabase.txt", e);
    }
  }
}
