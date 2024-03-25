/**
 * The {@code Callback} interface provides a mechanism for receiving notifications when a user
 * submits an answer. Classes that implement this interface can handle the user's response and
 * perform actions accordingly.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public interface Callback {
  /**
   * Handles the submission of an answer by the user.
   *
   * @param userResponse The user's response.
   * @param correctAnswer The correct answer against which the user's response is compared.
   */
  void answerSubmitted(String userResponse, String correctAnswer);
}
