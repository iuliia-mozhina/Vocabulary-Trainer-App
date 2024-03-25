import java.util.List;
import java.util.Random;

/**
 * The {@code Question} class represents a question in a vocabulary trainer game. It includes
 * functionality for setting and retrieving the question type.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class Question {
  /** The type of the question, such as "Multiple Choice" or "Translation." */
  private String questionType;

  /**
   * Constructs a new {@code Question} instance with an initial question type set to an empty
   * string.
   */
  public Question() {
    this.questionType = "";
  }

  /**
   * Retrieves the question type.
   *
   * @return The question type.
   */
  public String getQuestionType() {
    return questionType;
  }

  /**
   * Randomly sets the question type from the provided list of available types.
   *
   * @param availableQuestionTypes The list of available question types.
   * @throws IllegalArgumentException if the list of available question types is null or empty.
   */
  public void setQuestionType(final List<String> availableQuestionTypes) {
    if (availableQuestionTypes == null || availableQuestionTypes.isEmpty()) {
      throw new IllegalArgumentException("List of question types cannot be null or empty.");
    }

    final Random random = new Random();
    final int randomIndex = random.nextInt(availableQuestionTypes.size());
    this.questionType = availableQuestionTypes.get(randomIndex);
  }
}
