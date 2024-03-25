import java.util.List;

/**
 * The {@code VocabularyFactory} interface defines methods for creating language-related objects
 * within the context of an abstract factory design pattern. It serves as the abstract factory
 * interface for creating instances of {@link Language}, {@link Word}, and {@link VocabularyLevel}.
 *
 * <p>This interface follows the abstract factory design pattern, where a family of related or
 * dependent objects is created without specifying their concrete classes.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public interface VocabularyFactory {
  /**
   * Creates a new language instance based on the specified vocabulary level.
   *
   * @param level the vocabulary level for which to create a language
   * @return a new Language instance
   */
  Language createLanguage(VocabularyLevel level);

  /**
   * Creates a list of words based on the specified vocabulary level.
   *
   * @param level the vocabulary level for which to create words
   * @return a list of Word instances
   */
  List<Word> createWord(String level);

  /**
   * Creates a new vocabulary level instance based on the specified level name.
   *
   * @param level the name of the vocabulary level
   * @return a new VocabularyLevel instance
   */
  VocabularyLevel createVocabularyLevel(String level);
}
