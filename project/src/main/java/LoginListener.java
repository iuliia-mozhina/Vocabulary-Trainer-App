/**
 * The {@code LoginListener} interface provides a contract for classes that wish to be notified when
 * a user attempts to log in.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
interface LoginListener {
  /**
   * Called when a user attempts to log in.
   *
   * @param username The username entered by the user.
   * @param password The password entered by the user.
   * @param user The associated user object.
   */
  void onLogin(String username, String password, Nutzer user);
}
