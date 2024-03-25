import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code Nutzer} class represents a user in the system. It includes information such as the
 * username, high score, selected language, and selected level.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class Nutzer {
  /** The file name for the user database. */
  private static final String USER_DATABASE_FILE = "./userDatabase.txt";

  /** The username of the user. */
  private String username;

  /** The high score achieved by the user. */
  private int highScore;

  /** The selected language to learn of the user. */
  private String selectedLanguage;

  /** The selected level of the user. */
  private String selectedLevel;

  /**
   * Constructs a new {@code Nutzer} object with the specified username, selected language, and
   * selected level. Reads and sets the high score from the userDatabase.txt file.
   *
   * @param username The username of the user.
   * @param selectedLanguage The selected language preference of the user.
   * @param selectedLevel The selected level of the user.
   */
  public Nutzer(final String username, final String selectedLanguage, final String selectedLevel) {
    this.username = username;
    this.selectedLanguage = selectedLanguage;
    this.selectedLevel = selectedLevel;

    // Read and set high score from userDatabase.txt
    setHighScore();
  }

  /**
   * Gets the username of the user.
   *
   * @return The username of the user.
   */
  public String getUserName() {
    return username;
  }

  /**
   * Gets the high score achieved by the user given as a parameter.
   *
   * @return The high score of the user.
   */
  public static int getUserHighscore(Nutzer user) {return user.highScore;}

  /**
   * Sets the username of the user.
   *
   * @param username The new username of the user.
   */
  public void setUsername(final String username) {
    if (username == null || username.trim().isEmpty()) {
      throw new IllegalArgumentException("Username cannot be null or empty");
    }
    this.username = username;
  }

  /** Sets the high score by reading it from the file. */
  public void setHighScore() {
    this.highScore = readHighScoreFromFile();
  }

  /**
   * Gets the selected language of the user.
   *
   * @return The selected language of the user.
   */
  public String getSelectedLanguage() {
    return this.selectedLanguage;
  }

  /**
   * Sets the selected language of the user.
   *
   * @param language The selected language of the user.
   */
  public void setSelectedLanguage(final String language) {
    this.selectedLanguage = language;
  }

  /**
   * Gets the selected level of the user.
   *
   * @return The selected level of the user.
   */
  public String getSelectedLevel() {
    return this.selectedLevel;
  }

  /**
   * Sets the selected level of the user.
   *
   * @param level The selected level of the user.
   */
  public void setSelectedLevel(final String level) {
    this.selectedLevel = level;
  }

  private int readHighScoreFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATABASE_FILE))) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String[] parts = line.split(",");
        if (parts.length >= 2 && parts[0].equals(username)) {
          // Return the high score if the username is found
          return Integer.parseInt(parts[1].trim());
        }
      }
    } catch (IOException | NumberFormatException e) {
      Logger.getLogger(Nutzer.class.getName())
              .log(Level.SEVERE, "Error reading high score from file", e);
    }
    // Return 0 if the user or high score is not found
    return 0;
  }

  /**
   * Updates the high score in the userDatabase.txt file based on the obtained points in a play
   * round.
   *
   * @param sessionPoints The points earned in the current session to be added to the high score.
   */
  /**
   * Updates the high score of the user in the userDatabase.txt file.
   *
   * @param newHighScore The new high score to be set.
   */
  private void updateHighScoreInFile(int newHighScore) {
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATABASE_FILE));
         BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATABASE_FILE + "_temp"))) {

      String line;
      boolean userUpdated = false;

      while ((line = reader.readLine()) != null) {
        final String[] parts = line.split(",");
        if (parts.length >= 2 && parts[0].equals(username)) {
          // Update high score if the username is found
          if (parts.length >= 4) {
            // Check if there are at least 4 columns (for registered users)
            line =
                    username.trim()
                            + ", "
                            + newHighScore
                            + ", "
                            + parts[2].trim()
                            + ", "
                            + parts[3].trim();
          } else {
            // Less than 4 columns (guest users)
            line = username.trim() + ", " + newHighScore;
          }
          userUpdated = true;
        }
        writer.write(line + "\n");
      }

      if (!userUpdated) {
        // If the user is not found in the file, add a new entry
        writer.write(username.trim() + ", " + newHighScore + "\n");
      }

    } catch (IOException e) {
      Logger.getLogger(Nutzer.class.getName())
              .log(Level.SEVERE, "Error updating high score in file", e);
    }

    // Rename the temporary file to the original file
    final File tempFile = new File(USER_DATABASE_FILE + "_temp");
    final File originalFile = new File(USER_DATABASE_FILE);

    if (tempFile.renameTo(originalFile)) {
      Logger.getLogger(Nutzer.class.getName()).log(Level.INFO, "High score updated successfully");
    } else {
      Logger.getLogger(Nutzer.class.getName()).log(Level.SEVERE, "Failed to update high score");
    }

    this.highScore = newHighScore;
  }

  /**
   * Resets the high score of the user to 0 in the userDatabase.txt file.
   */
  public void resetHighScore() {
    updateHighScoreInFile(0);
  }

  /**
   * Updates the high score in the userDatabase.txt file based on the obtained points in a play round.
   *
   * @param sessionPoints The points earned in the current session to be added to the high score.
   */
  public void updateHighScore(final int sessionPoints) {
    if (sessionPoints < 0 || sessionPoints > PlayRound.getMaxQuestions()) {
      throw new IllegalArgumentException("Session points must be a positive integer" +
              " and cannot exceed maximum number of questions allowed");
    }

    // Read the existing high score from the file
    final int currentHighScore = readHighScoreFromFile();

    // Update the high score by adding session points
    final int newHighScore = currentHighScore + sessionPoints;

    updateHighScoreInFile(newHighScore);
  }
}
