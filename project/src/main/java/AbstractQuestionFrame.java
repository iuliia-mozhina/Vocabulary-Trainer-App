import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.Serial;
import java.util.List;

/**
 * An abstract class representing a frame for displaying a question in a vocabulary trainer.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public abstract class AbstractQuestionFrame extends JFrame {

  @Serial private static final long serialVersionUID = 1L;

  /** Flag indicating whether an answer has been submitted for the question. */
  protected boolean answerSubmitted;

  /** The content pane for the frame. */
  protected JPanel contentPane;

  /** The word associated with the question. */
  protected Word word;

  /** The user's response to the question. */
  protected String userResponse;

  /** The callback to handle submitted answers. */
  protected Callback callback;

  /** The current user of the vocabulary trainer. */
  protected Nutzer currentUser;

  /**
   * Constructs a new QuestionFrame instance.
   *
   * @param word The word associated with the question.
   * @param currentUser The current user of the vocabulary trainer.
   * @param callback The callback to handle submitted answers.
   */
  public AbstractQuestionFrame(final Word word, final Nutzer currentUser, final Callback callback) {
    this.word = word;
    this.currentUser = currentUser;
    this.callback = callback;
    this.answerSubmitted = false;
  }

  /** Abstract method to handle getting the user's answer to the question. */
  protected abstract void getAnswer();

  /** Abstract method to navigate back to the main menu. */
  protected void toMainMenu(){    closeFrame();
    final java.util.List<String> availableLanguages = java.util.List.of("Spanish", "Russian");
    final java.util.List<String> availableLevels = List.of("Beginner", "Intermediate", "Advanced");
    new MainMenu(
            availableLanguages,
            availableLevels,
            this.currentUser.getSelectedLanguage(),
            this.currentUser.getSelectedLevel(),
            currentUser)
            .setVisible(true);};

  /** Initializes the frame with common settings and components. */
  protected void initializeFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    setLocationRelativeTo(null);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    final JButton checkBtn = new JButton("Check!");
    checkBtn.addActionListener(e -> getAnswer());
    checkBtn.setBounds(173, 218, 117, 29);
    contentPane.add(checkBtn);

    final JButton tomainBtn = new JButton("main menu");
    tomainBtn.addActionListener(e -> toMainMenu());
    tomainBtn.setBounds(327, 6, 117, 29);
    contentPane.add(tomainBtn);
  }

  /** Closes the frame by disposing of its resources. */
  protected void closeFrame() {
    dispose();
  }

  /**
   * Retrieves the current user of the vocabulary trainer.
   *
   * @return The current user.
   */
  protected Nutzer getCurrentUser() {
    return currentUser;
  }
}
