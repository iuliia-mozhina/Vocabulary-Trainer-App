import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the {@link MultipleQuestion} class.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class MultipleQuestionTest {
  private MultipleQuestion classUnderTest;

  /**
   * Sets up the test environment by creating a {@link MultipleQuestion} instance with predefined
   * data for testing.
   */
  @Before
  public void setUp() {
    Word word = new Word("Hola", "Hello");
    List<Word> words =
        Arrays.asList(
            new Word("Hola", "Hello"),
            new Word("Amigo", "Friend"),
            new Word("Rojo", "Red"),
            new Word("Gato", "Cat"));
    Nutzer currentUser = new Nutzer("John", "Spanish", "Beginner");
    Callback callback = new MockCallback();
    classUnderTest = new MultipleQuestion(word, words, currentUser, callback);
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when the correct answer is option1 and
   * it's been selected.
   */
  @Test
  public void testGetAnswerCorrectOption1() {
    classUnderTest.option1.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when the correct answer is option2 and
   * it's been selected.
   */
  @Test
  public void testGetAnswerCorrectOption2() {
    Word word = new Word("Amigo", "Friend");
    classUnderTest =
        new MultipleQuestion(
            word,
            classUnderTest.getWords(),
            classUnderTest.getCurrentUser(),
            classUnderTest.getCallback());

    classUnderTest.option2.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when the correct answer is option3 and
   * it's been selected.
   */
  @Test
  public void testGetAnswerCorrectOption3() {
    Word word = new Word("Rojo", "Red");
    classUnderTest =
        new MultipleQuestion(
            word,
            classUnderTest.getWords(),
            classUnderTest.getCurrentUser(),
            classUnderTest.getCallback());

    classUnderTest.option3.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when the correct answer is option4 and
   * it's been selected.
   */
  @Test
  public void testGetAnswerCorrectOption4() {
    Word word = new Word("Gato", "Cat");
    classUnderTest =
        new MultipleQuestion(
            word,
            classUnderTest.getWords(),
            classUnderTest.getCurrentUser(),
            classUnderTest.getCallback());

    classUnderTest.option4.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
  }

  /** Tests the {@link MultipleQuestion#getAnswer()} method when no option is selected. */
  @Test
  public void testGetAnswerNoOptionSelected() {
    classUnderTest.getAnswer();

    assertFalse(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when option 1 is selected but it's
   * incorrect.
   */
  @Test
  public void testGetAnswerIncorrectOptionSelected1() {
    Word word = new Word("Gato", "Cat");
    classUnderTest =
        new MultipleQuestion(
            word,
            classUnderTest.getWords(),
            classUnderTest.getCurrentUser(),
            classUnderTest.getCallback());

    classUnderTest.option1.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertTrue(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when option 2 is selected but it's
   * incorrect.
   */
  @Test
  public void testGetAnswerIncorrectOptionSelected2() {
    classUnderTest.option2.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertTrue(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when option 3 is selected but it's
   * incorrect.
   */
  @Test
  public void testGetAnswerIncorrectOptionSelected3() {
    classUnderTest.option3.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option2.isSelected());
    assertTrue(classUnderTest.option3.isSelected());
    assertFalse(classUnderTest.option4.isSelected());
  }

  /**
   * Tests the {@link MultipleQuestion#getAnswer()} method when option 4 is selected but it's
   * incorrect.
   */
  @Test
  public void testGetAnswerIncorrectOptionSelected4() {
    classUnderTest.option4.setSelected(true);
    classUnderTest.getAnswer();

    assertTrue(classUnderTest.isAnswerSubmitted());
    assertFalse(classUnderTest.option1.isSelected());
    assertFalse(classUnderTest.option2.isSelected());
    assertFalse(classUnderTest.option3.isSelected());
    assertTrue(classUnderTest.option4.isSelected());
  }
}
