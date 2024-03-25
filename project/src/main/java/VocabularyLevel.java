/**
 * The {@code VocabularyLevel} class represents a level of vocabulary. It includes methods to access
 * the level name and is intended to be used in conjunction with a list of words.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class VocabularyLevel {
  /** The name of the level. */
  private final String levelName;

  /**
   * Constructs a new VocabularyLevel with the specified level name.
   *
   * @param levelName the name of the vocabulary level
   */
  public VocabularyLevel(final String levelName) {
    this.levelName = levelName;
  }

  /**
   * Gets the name of the vocabulary level.
   *
   * @return the name of the vocabulary level
   */
  public String getLevelName() {
    return levelName;
  }
}
