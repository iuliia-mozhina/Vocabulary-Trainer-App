/**
 * This class is a mock implementation of the {@link Callback} interface for testing purposes. It
 * does not perform any actions when the {@link #answerSubmitted(String, String)} method is called.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class MockCallback implements Callback {

  /**
   * Empty implementation of the answerSubmitted method. This method is called when an answer is
   * submitted.
   *
   * @param submittedAnswer The answer submitted by the user.
   * @param correctAnswer The correct answer for the question.
   */
  @Override
  public void answerSubmitted(String submittedAnswer, String correctAnswer) {}
}
