
package ui;

import model.Prompt;
import model.PromptList;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI extends JFrame implements ActionListener {

    private static final String PROMPT_FILE = "./data/promptlist.json";
    private PromptList promptList;
    private Prompt prompt;

    private JPanel mainMenu;
    private JButton addPromptButton;
    private JButton removePromptButton;
    private JButton saveQuizButton;
    private JButton loadQuizButton;
    private JButton viewQuizButton;

    private JPanel promptsPanel; // carListingsPanel
    private JLabel quizzes; //listings


    private JPanel quizPage; //listingPage. shows all prompts added
    private JTextField questionTextField;
    private JTextField answerTextField;
    private JTextField difficultyTextField;
    private JLabel question;
    private JLabel answer;
    private JLabel difficulty;

    public GUI() {
        super("Your Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 700));
        initializeMenu();
        currentQuizPanel(); //makeCarListingsPanel display current prompts
        addPromptPanel(); //makeListYourCarPanel panel that pops up when u want to add a prompt

        JLabel welcomeLabel = new JLabel("Welcome to Your Quiz!");
        JLabel mainScreenImage = new JLabel();
        addLabel(welcomeLabel);
        //addImageToLabel(mainScreenImage);

        initializeMenuButtons();

        addButtons(viewQuizButton, removePromptButton, saveQuizButton, loadQuizButton, addPromptButton);

        addButtonFunctionality(); //addActionToButton

        mainMenu.setVisible(true);
    }

    public void initializeMenu() {
        mainMenu = new JPanel();
        add(mainMenu);
        quizzes = new JLabel();
        quizzes.setText("No quiz available");
    }

    public void initializeMenuButtons() {
        removePromptButton = new JButton("Remove a prompt");
        saveQuizButton = new JButton("Save Quiz");
        loadQuizButton = new JButton("Load Quiz");
        viewQuizButton = new JButton("View your current quiz");
    }

    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.white);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button4, JButton button5) {
        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);

    }

    public void addMenuButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void addLabel(JLabel j1) {
        j1.setFont(new Font("ComicSans", Font.BOLD, 30));
        mainMenu.add(j1);
    }

//    public void addImageToLabel(JLabel j1) {
//        j1.setIcon(new ImageIcon("./data/tobs.jpg"));
//        j1.setMinimumSize(new Dimension(20,20));
//        mainMenu.add(j1);
//    }


    public void addButtonFunctionality() {

        removePromptButton.addActionListener(this);
        removePromptButton.setActionCommand("Remove");
        saveQuizButton.addActionListener(this);
        saveQuizButton.setActionCommand("Save");
        loadQuizButton.addActionListener(this);
        loadQuizButton.setActionCommand("Load");
        viewQuizButton.addActionListener(this);
        viewQuizButton.setActionCommand("View");
        addPromptButton.addActionListener(this);
        addPromptButton.setActionCommand("Add");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("View")) {
            //displayImage()
            initializePromptPanel();
        } else if (ae.getActionCommand().equals("Remove")) {
            //displayImage()
            removePrompt(prompt);
        } else if (ae.getActionCommand().equals("Save")) {
            //saveQuiz();
        } else if (ae.getActionCommand().equals("Load")) {
            //loadQuiz();
        } else if (ae.getActionCommand().equals("Add")) {
            addPromptToQuiz();
            addAPrompt();
        } else if (ae.getActionCommand().equals(("Menu"))) {
            returnToMainMenu();
        }
    }

    public void addPromptPanel() {

        quizPage = new JPanel(new GridLayout(0, 2));
        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, quizPage);

        createPromptPage();
        addLabelToPrompts();

    }

    public void addPromptToQuiz() {
        add(quizPage);
        quizPage.setVisible(true);
        mainMenu.setVisible(false);
        promptsPanel.setVisible(false);
    }


    public void createPromptPage() {
        addPromptButton = new JButton("Add a prompt");
        addPromptButton.setActionCommand("Add");
        addPromptButton.addActionListener(this);

        question = new JLabel("Enter question:");
        questionTextField = new JTextField(1);

        answer = new JLabel("Enter answer:");
        answerTextField = new JTextField(1);

        difficulty = new JLabel(("Enter true for hard and false for easy:"));
        difficultyTextField = new JTextField(1);

        labelStructure();
    }


    public void addLabelToPrompts() {
        quizPage.add(addPromptButton);

        quizPage.add(question);
        quizPage.add(questionTextField);
        quizPage.add(answer);
        quizPage.add(answerTextField);
        quizPage.add(difficulty);
        quizPage.add(difficultyTextField);
    }

    public void labelStructure() {
        addPromptButton.setBackground(Color.BLACK);
        addPromptButton.setForeground(Color.WHITE);
        addPromptButton.setFont(new Font("Arial", Font.BOLD, 12));

        question.setFont(new Font("ComicSans", Font.BOLD, 15));
        answer.setFont(new Font("ComicSans", Font.BOLD, 15));
        difficulty.setFont(new Font("ComicSans", Font.BOLD, 15));

        questionTextField.setMaximumSize(new Dimension(10, 5));
        answerTextField.setMaximumSize(new Dimension(1200, 400));
        difficultyTextField.setMaximumSize(new Dimension(1200, 400));
    }


    public void addAPrompt() {
        try {
            prompt = new Prompt(questionTextField.getText(), answerTextField.getText(),
                    Boolean.valueOf(difficultyTextField.getText()));
            promptList.addPrompt(prompt);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again");
        } catch (IndexOutOfBoundsException e) {
            quizzes.setText("Please initialize quiz file before proceeding");
        }
    }

    public void removePrompt(Prompt p) {
        try {
            promptList.removePrompt(p);
        } catch (NullPointerException e) {
            System.out.println("Please add a car before attempting to remove it");
        } catch (IndexOutOfBoundsException e) {
            quizzes.setText("Please initialize quiz file before proceeding");
        }
    }

    public void currentQuizPanel() {
        promptsPanel = new JPanel(new GridLayout(2, 1));
        JScrollPane scroll = new JScrollPane(quizzes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, promptsPanel);
    }

    public void initializePromptPanel() {
        add(promptsPanel);
        promptsPanel.setVisible(true);
        mainMenu.setVisible(false);
        quizPage.setVisible(false);
    }

    public void returnToMainMenu() {
        mainMenu.setVisible(true);
        promptsPanel.setVisible(false);
        quizPage.setVisible(false);
    }



}







