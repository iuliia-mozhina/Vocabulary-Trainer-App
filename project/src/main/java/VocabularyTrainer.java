/**
 * The {@code VocabularyTrainer} class represents a singleton instance that manages the state of a
 * vocabulary trainer application. It includes functionality to update the current language and
 * level, as well as access the current language and level settings.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public final class VocabularyTrainer {
  /** The singleton instance of the VocabularyTrainer */
  private static VocabularyTrainer instance;

  /** The current language of the VocabularyTrainer */
  private Language currentLanguage;

  /** The current level of the VocabularyTrainer */
  private VocabularyLevel currentLevel;

  /** Private constructor (to ensure the implementation of the singleton pattern) */
  private VocabularyTrainer() {}

  /**
   * Gets the singleton instance of the {@code VocabularyTrainer}.
   *
   * @return The singleton instance of the {@code VocabularyTrainer}.
   */
  public static VocabularyTrainer getInstance() {
    if (instance == null) {
      instance = new VocabularyTrainer();
    }
    return instance;
  }

  /**
   * Sets the {@code VocabularyFactory} for word generation. Any factory that implements {@code
   * VocabularyFactory} can be accepted.
   *
   * @param factory The {@code VocabularyFactory} to set.
   */
  public void setVocabularyFactory(VocabularyFactory factory) {}

  /**
   * Updates the current language and level settings of the vocabulary trainer.
   *
   * @param language The new language setting.
   * @param level The new level setting.
   */
  public void updateLanguageAndLevel(final Language language, final VocabularyLevel level) {
    this.currentLanguage = language;
    this.currentLevel = level;
  }

  /**
   * Retrieves the current language setting of the vocabulary trainer.
   *
   * @return The current language setting.
   */
  public Language getCurrentLanguage() {
    return currentLanguage;
  }

  /**
   * Retrieves the current level setting of the vocabulary trainer.
   *
   * @return The current level setting.
   */
  public VocabularyLevel getCurrentLevel() {
    return currentLevel;
  }
}
