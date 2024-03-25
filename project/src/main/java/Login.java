import java.awt.event.ActionEvent;
import java.io.Serial;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The {@code Login} class represents the main login window of the application. It allows users to
 * log in, register, or continue as a guest.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public class Login extends JFrame {
  @Serial private static final long serialVersionUID = 1L;

  /** The minimum length required for a username. */
  private static final int MIN_USERNAME_LENGTH = 5;

  /** Text field for entering username. */
  private JTextField nameField;

  /** Text field for entering password. */
  private JPasswordField passwordField;

  /** Listener for handling login events. */
  private LoginListener loginListener;

  /** Strategy for handling login (e.g., regular login or guest login). */
  private LoginStrategy loginStrategy;

  /**
   * Adds a {@code LoginListener} to handle login events.
   *
   * @param listener the login listener to be added
   */
  public void addLoginListener(final LoginListener listener) {
    this.loginListener = listener;
  }

  /**
   * Sets the login strategy for handling login attempts.
   *
   * @param strategy the login strategy to be set
   */
  public void setLoginStrategy(final LoginStrategy strategy) {
    this.loginStrategy = strategy;
  }

  /** Displays the components of the login window. */
  public void loginMenu() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    final JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    final JButton loginBtn = createLoginButton();
    contentPane.add(loginBtn);

    final JButton registerBtn = createRegisterButton();
    contentPane.add(registerBtn);

    nameField = new JTextField();
    nameField.setBounds(161, 54, 204, 26);
    contentPane.add(nameField);
    nameField.setColumns(10);

    final JLabel userLabel = new JLabel("User name");
    userLabel.setBounds(55, 59, 71, 16);
    contentPane.add(userLabel);

    final JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setBounds(55, 97, 61, 16);
    contentPane.add(passwordLabel);

    final JButton guestLoginButton = createGuestLoginButton();
    contentPane.add(guestLoginButton);

    passwordField = new JPasswordField();
    passwordField.setBounds(161, 92, 204, 26);
    contentPane.add(passwordField);
  }

  /**
   * Creates a login button for registered users.
   *
   * @return the created login button
   */
  private JButton createLoginButton() {
    final JButton loginBtn = new JButton("Login");
    loginBtn.addActionListener(this::loginButtonAction);
    loginBtn.setBounds(232, 160, 117, 29);
    return loginBtn;
  }

  /**
   * Handles the action when the login button is pressed.
   *
   * @param e the action event
   */
  private void loginButtonAction(final ActionEvent e) {
    final String enteredUsername = nameField.getText();
    final String enteredPassword = new String(passwordField.getPassword());

    // Check if both username and password are entered
    if (enteredUsername.isEmpty()) {
      showError("Please enter a valid username!");
    } else if (enteredPassword.isEmpty()) {
      showError("Please enter a valid password!");
    } else {
      setLoginStrategy(new RegularLoginStrategy());
      regularLogin(enteredUsername, enteredPassword);
    }
  }

  /**
   * Creates a register button.
   *
   * @return the created register button
   */
  private JButton createRegisterButton() {
    final JButton registerBtn = new JButton("Register");
    registerBtn.addActionListener(
        e -> {
          final Registrierung registrationWindow = new Registrierung(loginListener, loginStrategy);
          registrationWindow.setVisible(true);
        });
    registerBtn.setBounds(103, 160, 117, 29);
    return registerBtn;
  }

  /**
   * Displays an error message dialog with the specified message.
   *
   * @param errorMessage the error message to be displayed
   */
  private void showError(final String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Handles a regular (for registered users) login attempt.
   *
   * @param enteredUsername the entered username
   * @param enteredPassword the entered password
   */
  private void regularLogin(final String enteredUsername, final String enteredPassword) {
    if (loginStrategy instanceof RegularLoginStrategy regularLoginStrategy) {
      final RegistrierterNutzer registeredUser =
          new RegistrierterNutzer(enteredUsername, null, null);

      if (!regularLoginStrategy.login(enteredUsername, enteredPassword)) {
        showError("Please enter the correct password!");
      } else {
        if (loginListener != null) {
          String email = registeredUser.getEmailAddress();
          String password = registeredUser.getPassword();

          registeredUser.setUsername(enteredUsername);
          registeredUser.setHighScore();
          registeredUser.setEmail(email);
          registeredUser.setPassword(password);

          loginListener.onLogin(enteredUsername, enteredPassword, registeredUser);
        }
      }
    }
  }

  /**
   * Creates a guest login button.
   *
   * @return the created guest login button
   */
  private JButton createGuestLoginButton() {
    final JButton guestLoginButton = new JButton("Continue without registration");
    guestLoginButton.addActionListener(
        e -> {
          setLoginStrategy(new GuestLoginStrategy());
          guestLogin();
        });
    guestLoginButton.setBounds(103, 201, 246, 29);
    return guestLoginButton;
  }

  /** Opens a new window for entering the username only (guest login). */
  private void guestLogin() {
    final JFrame usernameOnlyFrame = createUsernameOnlyWindow();
    usernameOnlyFrame.setVisible(true);
  }

  /**
   * Creates a window for entering the username only.
   *
   * @return the created username-only window
   */
  private JFrame createUsernameOnlyWindow() {
    final JFrame usernameOnlyFrame = new JFrame();
    usernameOnlyFrame.setBounds(100, 100, 300, 150);
    final JPanel usernameOnlyPanel = new JPanel();
    usernameOnlyFrame.getContentPane().add(usernameOnlyPanel);
    usernameOnlyPanel.setLayout(null);

    final JLabel lblNewLabel = new JLabel("Enter User name:");
    lblNewLabel.setBounds(20, 30, 150, 16);
    usernameOnlyPanel.add(lblNewLabel);

    final JTextField usernameOnlyField = new JTextField();
    usernameOnlyField.setBounds(150, 30, 120, 20);
    usernameOnlyPanel.add(usernameOnlyField);

    final JButton confirmUsernameBtn =
        createConfirmUsernameButton(usernameOnlyFrame, usernameOnlyField);
    usernameOnlyPanel.add(confirmUsernameBtn);

    return usernameOnlyFrame;
  }

  /**
   * Creates a button for confirming the entered username.
   *
   * @param frame the parent frame
   * @param field the text field for entering the username
   * @return the created confirm username button
   */
  private JButton createConfirmUsernameButton(final JFrame frame, final JTextField field) {
    final JButton confirmUsernameBtn = new JButton("Login");
    confirmUsernameBtn.addActionListener(e -> handleConfirmUsername(frame, field));
    confirmUsernameBtn.setBounds(100, 70, 100, 25);
    return confirmUsernameBtn;
  }

  /**
   * Handles the action when the confirm username button is pressed.
   *
   * @param frame the parent frame
   * @param field the text field for entering the username
   */
  private void handleConfirmUsername(final JFrame frame, final JTextField field) {
    final String enteredUsername = field.getText();
    if (enteredUsername.length() < MIN_USERNAME_LENGTH) {
      showError("Username must be at least " + MIN_USERNAME_LENGTH + " characters long.");
      frame.toFront();
    } else {
      if (loginListener != null && loginStrategy instanceof GuestLoginStrategy guestLoginStrategy) {
        final boolean loginSuccessful = guestLoginStrategy.login(enteredUsername, null);
        if (loginSuccessful) {
          final Nutzer guestUser = new Nutzer(enteredUsername, null, null);
          guestUser.setUsername(enteredUsername);
          guestUser.setHighScore();
          loginListener.onLogin(enteredUsername, null, guestUser);
          frame.dispose();
        }
      }
    }
  }
}
