/**
 * The {@code LoginStrategy} interface defines a contract for implementing different strategies for
 * user login.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public interface LoginStrategy {
  /**
   * Attempts to authenticate a user based on the provided username and password.
   *
   * @param username The username entered by the user.
   * @param password The password entered by the user.
   * @return {@code true} if the login is successful, {@code false} otherwise.
   */
  boolean login(String username, String password);
}
