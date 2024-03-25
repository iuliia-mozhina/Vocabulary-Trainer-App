/**
 * The {@code Word} class represents a word and its translation. It provides methods to access the
 * original word, translation, and a string representation of the word.
 *
 * <p>Instances of this class are immutable once created, and the original and translation values
 * cannot be modified after instantiation.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class Word {
  /** The word in the original language. */
  private final String original;

  /** The translation of the word. */
  private final String translation;

  /**
   * Constructs a new Word with the specified original and its translation
   *
   * @param original the word in the original language
   * @param translation the translation of the word
   */
  public Word(final String original, final String translation) {
    this.original = original;
    this.translation = translation;
  }

  /**
   * Gets the original word.
   *
   * @return the original word
   */
  public String getOriginal() {
    return original;
  }

  /**
   * Gets the translation of the word.
   *
   * @return the translation of the word
   */
  public String getTranslation() {
    return translation;
  }

  /**
   * Returns a string representation of the Word. The string representation is in the form
   * "(original, translation)".
   *
   * @return a string representation of the Word
   */
  @Override
  public String toString() {
    return "(" + original + ", " + translation + ")";
  }
}
