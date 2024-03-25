import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * The {@code RussianFactory} class is an implementation of the {@link VocabularyFactory} interface
 * specific to the Russian language. It provides methods to create instances of {@link Language},
 * {@link Word}, and {@link VocabularyLevel} with a focus on Russian vocabulary.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class RussianFactory implements VocabularyFactory {
  /**
   * Creates a new Russian language instance based on the specified vocabulary level.
   *
   * @param level the vocabulary level for which to create a Russian language
   * @return a new Language instance representing the Russian language
   */
  @Override
  public Language createLanguage(final VocabularyLevel level) {
    final List<Word> russianWords = createWord(level.getLevelName());
    return new Language("Russian", russianWords);
  }

  /**
   * Creates a list of Russian words based on the specified vocabulary level.
   *
   * @param level the vocabulary level for which to create Russian words
   * @return a list of Word instances representing Russian words
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
   * Creates a new Russian vocabulary level instance based on the specified level name.
   *
   * @param level the name of the Russian vocabulary level
   * @return a new VocabularyLevel instance representing the Russian vocabulary level
   */
  @Override
  public VocabularyLevel createVocabularyLevel(final String level) {
    return new VocabularyLevel(level);
  }
}
