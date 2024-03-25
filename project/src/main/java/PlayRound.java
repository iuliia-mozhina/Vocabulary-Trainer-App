import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code PlayRound} class represents a session where a user engages in multiple-choice and
 * translation questions, attempting to answer and accumulate points. The class implements the
 * {@code Callback} interface to receive notifications when a user submits an answer.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public class PlayRound implements Callback {

  /** The number of questions in a play round */
  private static final int MAX_QUESTIONS = 15;

  /** The list of words for the play round. */
  private final List<Word> wordsForPlayRound;

  /** The current user participating in the play round. */
  private final Nutzer currentUser;

  /** The session points accumulated during the play round. */
  private Integer sessionPoints;

  /** The index of the current question in the play round. */
  private int currentQuestionIndex;

  /**
   * Constructs a new {@code PlayRound} instance with the specified list of words for the play round
   * and the current user. Initializes session points, question index, and user details.
   *
   * @param wordsForPlayRound The list of words for the play round.
   * @param currentUser The current user participating in the play round.
   */
  public PlayRound(final List<Word> wordsForPlayRound, final Nutzer currentUser) {
    this.sessionPoints = 0;
    this.wordsForPlayRound = wordsForPlayRound;
    this.currentQuestionIndex = 0;
    this.currentUser = currentUser;
  }

  public static int getMaxQuestions() {
    return MAX_QUESTIONS;
  }

  /**
   * Gets a random word from the provided list of words.
   *
   * @param words The list of words to select from.
   * @return A randomly selected word.
   */
  private Word getRandomWord(final List<Word> words) {
    final Random random = new Random();
    final int randomIndex = random.nextInt(words.size());
    return words.get(randomIndex);
  }

  /**
   * Generates answer options for a multiple-choice question.
   *
   * <p>This method takes a correct answer and a list of words, shuffles the list, and selects three
   * incorrect answers. It then adds the correct answer at a random position to create a list of
   * four answer options.
   *
   * @param correctAnswer The correct answer.
   * @param words The list of words for generating options.
   * @return A list of four answer options.
   */
  public static List<Word> generateAnswerOptions(final Word correctAnswer, final List<Word> words) {
    final List<Word> answerOptions = new ArrayList<>();

    // Add three incorrect answers
    final List<Word> shuffledWords = new ArrayList<>(words);
    final Random random = new Random();
    for (int i = shuffledWords.size() - 1; i > 0; i--) {
      final int index = random.nextInt(i + 1);
      final Word temp = shuffledWords.get(index);
      shuffledWords.set(index, shuffledWords.get(i));
      shuffledWords.set(i, temp);
    }

    for (int i = 0; i < Math.min(3, shuffledWords.size()); i++) {
      if (!shuffledWords.get(i).equals(correctAnswer)) {
        answerOptions.add(shuffledWords.get(i));
      }
    }

    // Add the correct answer at a random position
    final int correctAnswerIndex = random.nextInt(answerOptions.size() + 1);
    answerOptions.add(correctAnswerIndex, correctAnswer);

    return answerOptions;
  }

  /** Starts the play round by initiating the first question. */
  public void startRound() {
    nextQuestion();
  }

  /**
   * Moves to the next question in the play round. If the maximum number of questions is reached,
   * ends the round.
   */
  private void nextQuestion() {
    if
    (currentQuestionIndex < wordsForPlayRound.size() && currentQuestionIndex < MAX_QUESTIONS) {
      final Word randomWord = getRandomWord(wordsForPlayRound);

      final Question questionInstance = new Question();
      questionInstance.setQuestionType(List.of("Multiple Choice", "Translation"));
      final String questionType = questionInstance.getQuestionType();

      if ("Translation".equalsIgnoreCase(questionType)) {
        new TranslationQuestion(randomWord, this.currentUser, this);
      } else if ("Multiple Choice".equalsIgnoreCase(questionType)) {
        final List<Word> otherWords = generateAnswerOptions(randomWord, wordsForPlayRound);
        new MultipleQuestion(randomWord, otherWords, this.currentUser, this);
      }
    } else {
      endRound();
    }
  }

  /** Ends the play round, saves points, and shows the main menu. */
  public void endRound() {
    savePoints();
    showMainMenu(this.currentUser);
  }

  /** Saves the session points for the current user. */
  private void savePoints() {
    if (this.currentUser != null) {
      this.currentUser.updateHighScore(this.sessionPoints);
    }
  }

  /** Shows the main menu. */
  public static void showMainMenu(Nutzer currentUser) {
    final List<String> availableLanguages = List.of("Spanish", "Russian");
    final List<String> availableLevels = List.of("Beginner", "Intermediate", "Advanced");
    new MainMenu(
            availableLanguages,
            availableLevels,
            currentUser.getSelectedLanguage(),
            currentUser.getSelectedLevel(),
            currentUser)
        .setVisible(true);
  }

  /**
   * Handles the submission of an answer by the user.
   *
   * @param userResponse The user's response.
   * @param correctAnswer The correct answer.
   */
  @Override
  public void answerSubmitted(final String userResponse, final String correctAnswer) {

    final boolean answerIsCorrect = userResponse.equalsIgnoreCase(correctAnswer);

    if (answerIsCorrect) {
      this.sessionPoints++;
    }
    // Move to the next question after the user has submitted an answer
    currentQuestionIndex++;
    nextQuestion();
  }
}
