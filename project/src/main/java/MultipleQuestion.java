import javax.swing.*;
import java.util.List;

/**
 * A class representing a multiple-choice question frame in the vocabulary trainer. Extends the
 * abstract class AbstractQuestionFrame.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public class MultipleQuestion extends AbstractQuestionFrame {
  /** A list of words for every question. */
  private final List<Word> words;

  /** Radio button for the first answer option. */
  JRadioButton option1;

  /** Radio button for the second answer option. */
  JRadioButton option2;

  /** Radio button for the third answer option. */
  JRadioButton option3;

  /** Radio button for the forth answer option. */
  JRadioButton option4;

  /** The group of radio buttons representing answer options. */
  private ButtonGroup buttonGroup;

  private boolean answerSubmitted;

  /**
   * Constructs a new MultipleQuestion instance.
   *
   * @param word The word associated with the question.
   * @param words The list of words representing answer options.
   * @param currentUser The current user of the vocabulary trainer.
   * @param callback The callback to handle submitted answers.
   */
  public MultipleQuestion(
      final Word word, final List<Word> words, final Nutzer currentUser, final Callback callback) {
    super(word, currentUser, callback);
    this.words = words;
    this.answerSubmitted = false;
    initializeFrame();
  }

  /** Displays the multiple-choice question frame with answer options. */
  public void initializeFrame() {
    super.initializeFrame();

    final JLabel targetWord = new JLabel(word.getOriginal());
    targetWord.setHorizontalAlignment(SwingConstants.CENTER);
    targetWord.setBounds(67, 116, 323, 16);
    contentPane.add(targetWord);

    buttonGroup = new ButtonGroup();
    option1 = new JRadioButton(this.words.getFirst().getTranslation());
    option1.setBounds(77, 148, 128, 23);
    contentPane.add(option1);
    buttonGroup.add(option1);

    option2 = new JRadioButton(this.words.get(1).getTranslation());
    option2.setBounds(77, 183, 128, 23);
    contentPane.add(option2);
    buttonGroup.add(option2);

    option3 = new JRadioButton(this.words.get(2).getTranslation());
    option3.setBounds(265, 148, 128, 23);
    contentPane.add(option3);
    buttonGroup.add(option3);

    option4 = new JRadioButton(this.words.get(3).getTranslation());
    option4.setBounds(265, 183, 128, 23);
    contentPane.add(option4);
    buttonGroup.add(option4);

    final JLabel lblNewLabel = new JLabel("What is the correct translation?");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(126, 75, 209, 16);
    contentPane.add(lblNewLabel);

    setVisible(true);

  }

  /**
   * Checks if an answer has been submitted.
   *
   * @return {@code true} if an answer has been submitted, {@code false} otherwise.
   */
  public boolean isAnswerSubmitted() {
    return answerSubmitted;
  }

  /** Gets the user's answer and processes it. */
  public void getAnswer() {
    if (buttonGroup.getSelection() == null) {
      JOptionPane.showMessageDialog(
          this, "Please select an option!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    } else if (option1.isSelected()) {
      this.userResponse = option1.getText();
    } else if (option2.isSelected()) {
      this.userResponse = option2.getText();
    } else if (option3.isSelected()) {
      this.userResponse = option3.getText();
    } else if (option4.isSelected()) {
      this.userResponse = option4.getText();
    }

    final boolean isCorrect = this.userResponse.equalsIgnoreCase(word.getTranslation());

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

    callback.answerSubmitted(this.userResponse, this.word.getTranslation());
    this.answerSubmitted = true;
  }

  /**
   * Retrieves the list of words associated with the question.
   *
   * @return The list of words.
   */
  public List<Word> getWords() {
    return words;
  }

  /**
   * Retrieves the callback associated with the question.
   *
   * @return The callback instance.
   */
  public Callback getCallback() {
    return callback;
  }
}
