import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code RegistrierterNutzer} class represents a registered user extending the {@code Nutzer}
 * class. It includes additional functionality to handle email and password.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class RegistrierterNutzer extends Nutzer {
  /** The logger for the {@code RegistrierterNutzer} class. */
  private static final Logger logger = Logger.getLogger(RegistrierterNutzer.class.getName());

  /** The file name for the user database. */
  private static final String USER_DATABASE_FILE = "./userDatabase.txt";

  /** The email of the user. */
  private String email;

  /** The password of the user. */
  private String password;

  /**
   * Constructs a new {@code RegistrierterNutzer} object. Reads and sets the email and password from
   * the userDatabase.txt file.
   *
   * @param username The username of the user.
   * @param selectedLanguage The selected language preference of the user.
   * @param selectedLevel The selected level of the user.
   */
  public RegistrierterNutzer(
      final String username, final String selectedLanguage, final String selectedLevel) {
    super(username, selectedLanguage, selectedLevel);
    // Set email and password from userDatabase.txt
    readEmailAndPasswordFromDatabase();
  }

  /**
   * Gets the email address of the registered user.
   *
   * @return The email address of the user.
   */
  public String getEmailAddress() {
    return email;
  }

  /**
   * Gets the password of the registered user.
   *
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the registered user.
   *
   * @param password The password of the user.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Sets the email address of the registered user.
   *
   * @param email The email address of the user.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Reads and sets the email and password from the userDatabase.txt file. The email and password
   * are associated with the current username.
   */
  private void readEmailAndPasswordFromDatabase() {
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATABASE_FILE))) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String[] parts = line.split(",");
        if (parts.length >= 4 && parts[0].equals(getUserName())) {
          // Username found, set email and password
          setEmail(parts[3].trim());
          setPassword(parts[2].trim());
          return;
        }
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error reading email and password from file", e);
    }
  }
}
