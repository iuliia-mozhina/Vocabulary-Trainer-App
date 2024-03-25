import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The {@code SpanishFactory} class is an implementation of the {@link VocabularyFactory} interface
 * specific to the Spanish language. It provides methods to create instances of {@link Language},
 * {@link Word}, and {@link VocabularyLevel} with a focus on Spanish vocabulary.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class SpanishFactory implements VocabularyFactory {
  /**
   * Creates a new Spanish language instance based on the specified vocabulary level.
   *
   * @param level the vocabulary level for which to create a Spanish language
   * @return a new Language instance representing the Spanish language
   */
  @Override
  public Language createLanguage(final VocabularyLevel level) {
    final List<Word> spanishWords = createWord(level.getLevelName());
    return new Language("Spanish", spanishWords);
  }

  /**
   * Creates a list of Spanish words based on the specified vocabulary level.
   *
   * @param level the vocabulary level for which to create Spanish words
   * @return a list of Word instances representing Spanish words
   */
  @Override
  public List<Word> createWord(final String level) {
    String filePath = String.format("/spanish_%s.xlsx", level.toLowerCase());
    try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
      if (inputStream != null) {
        return Utilities.readWordsFromExcel(inputStream);
      } else {
        throw new RuntimeException("Resource not found: " + filePath);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading resource: " + filePath, e);
    }
  }

  /**
   * Creates a new Spanish vocabulary level instance based on the specified level name.
   *
   * @param level the name of the Spanish vocabulary level
   * @return a new VocabularyLevel instance representing the Spanish vocabulary level
   */
  @Override
  public VocabularyLevel createVocabularyLevel(final String level) {
    return new VocabularyLevel(level);
  }
}
