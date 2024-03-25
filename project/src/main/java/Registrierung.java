import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrierung extends JFrame {
  private static final long serialVersionUID = 1L;
  private static final int MIN_PASSWORD_LENGTH = 5;
  private final JPanel contentPane;
  private JTextField textFieldUsername;
  private JTextField textFieldEmail;
  private JTextField textFieldPassword;
  private LoginListener loginListener;
  private LoginStrategy loginStrategy;
  private static final Logger logger = Logger.getLogger(Registrierung.class.getName());
  private static final String USER_DATABASE_FILE = "./userDatabase.txt";

  public Registrierung(LoginListener loginListener, LoginStrategy loginStrategy) {
    this.loginListener = loginListener;
    this.loginStrategy = loginStrategy;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 450, 300);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.setContentPane(this.contentPane);
    this.contentPane.setLayout(null);

    this.initializeUI();

    JButton btnNewButton = new JButton("Register");
    btnNewButton.setBounds(180, 178, 117, 29);
    btnNewButton.addActionListener(e -> handleRegistration());
    this.contentPane.add(btnNewButton);
  }

  private void handleRegistration() {
    String username = this.textFieldUsername.getText();
    String email = this.textFieldEmail.getText();
    String password = this.textFieldPassword.getText();

    if (!isValidEmail(email)) {
      showError("Please enter a valid email address.");
    } else if (password.length() < MIN_PASSWORD_LENGTH) {
      showError("Password must be at least 5 characters long!");
    } else {
      boolean registrationSuccess = writeUserToFile(username, email, password);
      if (registrationSuccess) {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
          if (loginListener != null && loginStrategy != null) {
            boolean loginSuccessful = loginStrategy.login(username, password);
            if (loginSuccessful) {
              RegistrierterNutzer registeredUser = new RegistrierterNutzer(username, null, null);
              registeredUser.setUsername(username);
              registeredUser.setHighScore();
              registeredUser.setEmail(email);
              registeredUser.setPassword(password);

              loginListener.onLogin(username, password, registeredUser);
            }
          }
        });
      }
    }
  }

  private boolean writeUserToFile(String username, String email, String password) {
    // Check if the file exists, create it if not
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATABASE_FILE))) {
      // File exists, continue with reading
    } catch (FileNotFoundException e) {
      // File not found, create the file
      try {
        new FileWriter(USER_DATABASE_FILE).close();
      } catch (IOException ex) {
        logError("Error creating userDatabase.txt", ex);
        return false;
      }
    } catch (IOException e) {
      logError("Error reading userDatabase.txt", e);
      return false;
    }

    // Write user information to the file
    try (PrintWriter writer = new PrintWriter(new FileWriter(USER_DATABASE_FILE, true))) {
      writer.println(username + ",0," + password + "," + email);
      return true;
    } catch (IOException e) {
      logError("Error writing to userDatabase.txt", e);
      return false;
    }
  }


  private boolean isValidEmail(String email) {
    Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  private void logError(String message, Exception exception) {
    logger.log(Level.SEVERE, message, exception);
    showError(message);
  }

  private void showError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  private void initializeUI() {
    this.textFieldUsername = new JTextField();
    this.textFieldUsername.setBounds(187, 27, 220, 26);
    this.contentPane.add(this.textFieldUsername);
    this.textFieldUsername.setColumns(10);
    this.textFieldEmail = new JTextField();
    this.textFieldEmail.setBounds(187, 57, 220, 26);
    this.contentPane.add(this.textFieldEmail);
    this.textFieldEmail.setColumns(10);
    JLabel usernameLabel = new JLabel("Username");
    usernameLabel.setBounds(43, 32, 77, 16);
    this.contentPane.add(usernameLabel);
    JLabel emailLabel = new JLabel("E-mail-address");
    emailLabel.setBounds(43, 60, 77, 21);
    this.contentPane.add(emailLabel);
    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setBounds(43, 93, 77, 16);
    this.contentPane.add(passwordLabel);
    this.textFieldPassword = new JTextField();
    this.textFieldPassword.setBounds(187, 88, 220, 26);
    this.contentPane.add(this.textFieldPassword);
    this.textFieldPassword.setColumns(10);
  }
}
