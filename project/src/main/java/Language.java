import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Language} class represents a language and its associated words. It includes methods to
 * access the language name, list of words, and a string representation of the language.
 *
 * <p>A language is required to have at least three words. If fewer than three words are provided
 * during instantiation, a message will be printed to the console, and the language object will be
 * created with an empty word list.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class Language {
  /** The name of the language */
  private final String name;

  /** The list of words in the corresponding language. */
  private final List<Word> words;

  /**
   * Constructs a new Language with the specified name and list of words.
   *
   * @param name the name of the language
   * @param words the list of words in the language
   */
  public Language(final String name, final List<Word> words) {
    this.name = name;
    this.words = new ArrayList<>(words);
  }

  /**
   * Gets the name of the language.
   *
   * @return the name of the language
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the list of words in the language.
   *
   * @return the list of words in the language
   */
  public List<Word> getWords() {
    return words;
  }

  /**
   * Returns a string representation of the Language. The string representation includes the name of
   * the language and its list of words.
   *
   * @return a string representation of the Language
   */
  @Override
  public String toString() {
    return "Language{" + "name='" + name + '\'' + ", words=" + words + '}';
  }
}
