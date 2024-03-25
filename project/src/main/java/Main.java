import java.util.List;
import javax.swing.*;

/**
 * The Main class responsible for starting the Vocabulary Trainer application.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public class Main {

  /**
   * The main method that starts the application by invoking the GUI creation.
   *
   * @param args The command-line arguments.
   */
  public static void main(final String[] args) {
    startMainGUI();
  }

  /** Starts the main GUI of the Vocabulary Trainer application. */
  public static void startMainGUI() {
    SwingUtilities.invokeLater(Main::createAndShowLoginWindow);
  }

  /** Creates and shows the login window. */
  private static void createAndShowLoginWindow() {
    final List<String> availableLanguages = List.of("Spanish", "Russian");
    final List<String> availableLevels = List.of("Beginner", "Intermediate", "Advanced");

    final Login loginWindow = new Login();
    loginWindow.loginMenu();
    loginWindow.setVisible(true);

    // Once the user logs in, proceed to open the main menu
    loginWindow.addLoginListener(
        (username, password, user) -> {
          loginWindow.dispose();
          openMainMenu(availableLanguages, availableLevels, user);
        });
  }

  /**
   * Opens the main menu with the provided parameters.
   *
   * @param availableLanguages The list of available languages.
   * @param availableLevels The list of available levels.
   * @param user The user who logged in.
   */
  private static void openMainMenu(
      final List<String> availableLanguages, final List<String> availableLevels, final Nutzer user) {
    new MainMenu(availableLanguages, availableLevels, "Spanish", "Beginner", user).setVisible(true);
  }
}
