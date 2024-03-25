import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;
import java.util.List;

/**
 * The {@code MainMenu} class represents the main menu of the vocabulary trainer application. It
 * extends {@code JFrame} and provides options to start learning, change user, view credits, and
 * access instructions.
 *
 * @author Alejandra Camelo Cruz
 * @author Iuliia Mozhina
 * @version 1.0
 */
public class MainMenu extends JFrame {
    @Serial private static final long serialVersionUID = 1L;

    /** List of available languages. */
    private final List<String> languages;

    /** List of available levels. */
    private final List<String> levels;

    /** Current user. */
    private final Nutzer currentUser;

    /** JComboBox for language selection. */
    private JComboBox<String> languageComboBox;

    /** JComboBox for level selection. */
    private JComboBox<String> levelComboBox;

    /** Current learning language of the application. */
    private String currentLanguage;

    /** Current learning level of the application. */
    private String currentLevel;

    /** JPanel to hold GUI components. */
    private JPanel contentPane;

    /**
     * Constructs a {@code MainMenu} object with the specified lists of languages and levels, the
     * current language and level, and the current user.
     *
     * @param languages The list of available languages.
     * @param levels The list of available levels.
     * @param currentLanguage The current language.
     * @param currentLevel The current level.
     * @param user The current user.
     */
    public MainMenu(
            final List<String> languages,
            final List<String> levels,
            final String currentLanguage,
            final String currentLevel,
            final Nutzer user) {
        this.currentUser = user;
        this.languages = languages;
        this.levels = levels;
        this.currentLanguage = currentLanguage; // Default language is Spanish
        this.currentLevel = currentLevel; // Default level is Beginner
        this.currentUser.setSelectedLanguage(currentLanguage); // Default language is Spanish
        this.currentUser.setSelectedLevel(currentLevel); // Default level is Beginner
        initializeGUI();
    }

    /** Initializes the graphical user interface of the main menu. */
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        selectLanguage();
        selectLevel();

        final JLabel userLabel = new JLabel("User: " + this.currentUser.getUserName());
        userLabel.setBounds(60, 39, 120, 16);
        contentPane.add(userLabel);

        final JLabel scoreLabel = new JLabel("Total score: " + Nutzer.getUserHighscore(this.currentUser));
        scoreLabel.setBounds(250, 39, 96, 16);
        contentPane.add(scoreLabel);

        final JButton changeUserBtn = new JButton("Change user");
        changeUserBtn.addActionListener(e -> changeUser());
        changeUserBtn.setBounds(246, 154, 161, 29);
        contentPane.add(changeUserBtn);

        final JButton instructionsBtn = new JButton("How to play?");
        instructionsBtn.addActionListener(e -> showInstructions());
        instructionsBtn.setBounds(73, 195, 161, 29);
        contentPane.add(instructionsBtn);

        final JButton creditsBtn = new JButton("Credits");
        creditsBtn.addActionListener(e -> showCredits());
        creditsBtn.setBounds(246, 195, 161, 29);
        contentPane.add(creditsBtn);

        final JButton start = new JButton("Start learning");
        start.addActionListener(e -> startVocabularyTrainer());
        start.setBounds(73, 154, 161, 29);
        contentPane.add(start);

        setVisible(true);
    }
    /** Starts the vocabulary trainer with the selected language and level. */
    private void startVocabularyTrainer() {
        VocabularyFactory factory;
        if (this.currentLanguage.equalsIgnoreCase("Russian")) {
            factory = new RussianFactory();
        } else {
            factory = new SpanishFactory();
        }

        // Obtain the VocabularyTrainer instance
        // achieved by calling the getInstance() method, since it's a singleton
        final VocabularyTrainer vocabularyTrainer = VocabularyTrainer.getInstance();

        vocabularyTrainer.setVocabularyFactory(factory);

        // Create Language and VocabularyLevel instances
        final Language language = factory.createLanguage(new VocabularyLevel(this.currentLevel));
        final VocabularyLevel level = factory.createVocabularyLevel(this.currentLevel);

        // Update language and level in the VocabularyTrainer
        vocabularyTrainer.updateLanguageAndLevel(language, level);

        // Create an instance of PlayRound
        final PlayRound playRound =
                new PlayRound(vocabularyTrainer.getCurrentLanguage().getWords(), this.currentUser);

        closeFrame();
        playRound.startRound();
    }

    /** Sets up the language selection components in the GUI. */
    private void selectLanguage() {
        final JLabel languageLabel = new JLabel("Learning:");
        languageLabel.setBounds(60, 67, 200, 16);
        this.contentPane.add(languageLabel);

        languageComboBox = new JComboBox<>(languages.toArray(new String[0]));
        languageComboBox.setSelectedItem(
                languages.get(languages.indexOf(this.currentUser.getSelectedLanguage())));
        languageComboBox.setBounds(122, 63, 100, 27);
        languageComboBox.addActionListener(
                e -> {
                    // Update the currentLanguage when an item is selected
                    final String selectedLanguage = (String) languageComboBox.getSelectedItem();
                    this.currentLanguage = selectedLanguage;
                    this.currentUser.setSelectedLanguage(selectedLanguage);
                });
        this.contentPane.add(languageComboBox);
    }

    /** Sets up the level selection components in the GUI. */
    private void selectLevel() {
        final JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(250, 67, 200, 16);
        this.contentPane.add(levelLabel);

        levelComboBox = new JComboBox<>(levels.toArray(new String[0]));
        levelComboBox.setSelectedItem(levels.get(levels.indexOf(this.currentUser.getSelectedLevel())));
        levelComboBox.setBounds(300, 63, 96, 27);
        levelComboBox.addActionListener(
                e -> {
                    // Update the currentLevel when an item is selected
                    final String selectedLevel = (String) levelComboBox.getSelectedItem();
                    this.currentLevel = selectedLevel;
                    this.currentUser.setSelectedLevel(selectedLevel);
                });
        this.contentPane.add(levelComboBox);
    }

    /** Displays the credits of developers in a new frame. */
    public void showCredits() {
        final JFrame creditsFrame = new JFrame("Credits");
        creditsFrame.setBounds(100, 100, 400, 200);
        final JPanel creditsPanel = new JPanel();
        creditsFrame.getContentPane().add(creditsPanel);
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));

        final JLabel nameLabel = new JLabel("Developed by:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsPanel.add(nameLabel);

        final JLabel developer1Label = new JLabel("Alejandra Camelo Cruz (camelocruz@uni-potsdam.de)");
        developer1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsPanel.add(developer1Label);

        final JLabel developer2Label = new JLabel("Iuliia Mozhina (mozhina@uni-potsdam.de)");
        developer2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsPanel.add(developer2Label);

        final JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> creditsFrame.dispose());
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsPanel.add(closeButton);

        creditsFrame.setVisible(true);
    }

    /** Displays the instructions for playing in a new frame. */
    public void showInstructions() {
        dispose();
        final InstructionsPlayRound instructionsPlayRound = new InstructionsPlayRound(this.currentUser);
        instructionsPlayRound.startInstructions();
    }

    /** Changes the user and navigates back to the main menu. */
    public void changeUser() {
        dispose();
        Main.startMainGUI();
    }

    /** Closes the current frame. */
    public void closeFrame() {
        // Release resources and close the frame
        dispose();
    }
}
