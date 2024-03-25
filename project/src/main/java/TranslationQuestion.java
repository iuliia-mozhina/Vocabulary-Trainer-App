import javax.swing.*;

/**
 * A class representing a translation question frame in the vocabulary trainer. Extends the abstract
 * class AbstractQuestionFrame.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public class TranslationQuestion extends AbstractQuestionFrame {
  /** Text field for user input. */
  private JTextField textField;

  /**
   * Constructs a new TranslationQuestion instance.
   *
   * @param word The word associated with the question.
   * @param currentUser The current user of the vocabulary trainer.
   * @param callback The callback to handle submitted answers.
   */
  public TranslationQuestion(final Word word, final Nutzer currentUser, final Callback callback) {
    super(word, currentUser, callback);
    initializeFrame();
  }

  /** Displays the translation question frame with input field. */
  public void initializeFrame() {
    super.initializeFrame();

    textField = new JTextField();
    textField.setBounds(123, 152, 203, 26);
    contentPane.add(textField);
    textField.setColumns(10);

    final JLabel lblNewLabel = new JLabel("Give the correct translation of the following word");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(67, 86, 323, 16);
    contentPane.add(lblNewLabel);

    final JLabel wordLabel = new JLabel(this.word.getOriginal());
    wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
    wordLabel.setBounds(67, 116, 323, 16);
    contentPane.add(wordLabel);

    setVisible(true);
  }

  /** Gets the user's answer and processes it. */
  public void getAnswer() {
    this.userResponse = textField.getText();
    if (this.userResponse == null || this.userResponse.isEmpty()) {
      JOptionPane.showMessageDialog(
          this, "Please give some input!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    String standardizedUserResponse = this.userResponse.toLowerCase();
    String standardizedCorrectTranslation = word.getTranslation().toLowerCase();

    final boolean isCorrect = standardizedUserResponse.equals(standardizedCorrectTranslation);

    if (isCorrect) {
      JOptionPane.showMessageDialog(
          this, "Correct! Well done!", "Result", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          this,
          "Incorrect. The correct translation is: " + word.getTranslation(),
          "Result",
          JOptionPane.ERROR_MESSAGE);
    }
    closeFrame();
    callback.answerSubmitted(this.userResponse, word.getTranslation());
    this.answerSubmitted = true;
  }
}
