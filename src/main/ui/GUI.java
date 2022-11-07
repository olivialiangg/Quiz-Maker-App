package ui;

import model.Prompt;
import model.PromptList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI extends JFrame implements ActionListener {

    private static final String PROMPT_FILE = "./data/promptlist.json";
    private PromptList prompts;
    private Prompt prompt;

    private JPanel mainMenu;
    private JButton removePromptButton;
    private JButton saveQuizButton;
    private JButton loadQuizButton;
    private JButton viewQuiz;

    private JPanel promptPanel;
    private JLabel quizzes;

    private JPanel quizPage;
    private JButton addPromptButton;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JLabel question;
    private JLabel answer;
    private JLabel difficulty;


    public GUI() {
        super("Your Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 700));
        initializeMenu();
        initializeQuizPanel();
        initializeMenuButtons();

        JLabel welcomeLabel = new JLabel("Welcome to Your Quiz!");
        JLabel mainScreenImage = new JLabel();
        addLabel(welcomeLabel);
        addImageToLabel(mainScreenImage);

        addButtons(viewQuiz, removePromptButton, saveQuizButton, loadQuizButton);

        addButtonFunctionality();

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
        saveQuizButton = new JButton("Save");
        loadQuizButton = new JButton("Load");
        viewQuiz = new JButton("View your quiz");
    }

    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.orange);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button4) {
        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);

    }

    public void addMenuButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.orange);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void addLabel(JLabel j1) {
        j1.setFont(new Font("ComicSans", Font.BOLD, 50));
        mainMenu.add(j1);
    }

    public void addImageToLabel(JLabel j1) {
        j1.setIcon(new ImageIcon("./data/tobs.jpg"));
        j1.setMinimumSize(new Dimension(20,20));
        mainMenu.add(j1);
    }


    public void addButtonFunctionality() {

        removePromptButton.addActionListener(this);
        removePromptButton.setActionCommand("Remove a prompt");
        saveQuizButton.addActionListener(this);
        saveQuizButton.setActionCommand("Save quiz");
        loadQuizButton.addActionListener(this);
        loadQuizButton.setActionCommand("Load quiz");
        viewQuiz.addActionListener(this);
        viewQuiz.setActionCommand("View your quiz");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Remove a prompt")) {
            removePrompt();
        } else if (e.getActionCommand().equals("Save quiz")) {
            savePrompts();
        } else if (e.getActionCommand().equals("Load quiz")) {
            loadPrompts();
        } else if (e.getActionCommand().equals("View your quiz")) {
            initializeQuizPanel();
        }
    }

    public void makeAddAPromptPanel() {
        promptPanel = new JPanel(new GridLayout(0, 2));
        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, promptPanel);

        createPromptPage();
        addLabelToPrompts();

    }

    public void initializeQuizPanel() {
        add(promptPanel);
        quizPage.setVisible(true);
        mainMenu.setVisible(false);
        promptPanel.setVisible(false);
    }

    public void createPromptPage() {
        addPromptButton = new JButton("Add a prompt");
        addPromptButton.setActionCommand("Add a prompt");
        addPromptButton.addActionListener(this);

        question = new JLabel("Enter question:");
        t1 = new JTextField(10);

        answer = new JLabel("Enter answer:");
        t2 = new JTextField(10);

        difficulty = new JLabel(("Enter true for hard and false for easy:"));
        t3 = new JTextField(10);

        labelStructure();
    }


    public void addLabelToPrompts() {
        promptPanel.add(addPromptButton);

        promptPanel.add(question);
        promptPanel.add(t1);
        promptPanel.add(answer);
        promptPanel.add(t2);
        promptPanel.add(difficulty);
        promptPanel.add(t3);
    }

    public void labelStructure() {
        addPromptButton.setBackground(Color.BLACK);
        addPromptButton.setForeground(Color.WHITE);
        addPromptButton.setFont(new Font("Arial", Font.BOLD, 12));

        question.setFont(new Font("ComicSans", Font.BOLD, 24));
        answer.setFont(new Font("ComicSans", Font.BOLD, 24));
        difficulty.setFont(new Font("ComicSans", Font.BOLD, 24));

        t1.setMaximumSize(new Dimension(1200, 400));
        t2.setMaximumSize(new Dimension(1200, 400));
        t3.setMaximumSize(new Dimension(1200, 400));
    }


    private void loadPrompts() throws IOException{

        try {
            database  = Reader.read(new File(CARLISTINGS_FILE));
            listings.setText("<html><pre>Current Listings: \n" + cars.getListings() + "\n</pre></html>");
            System.out.println("Listings loaded from file " + CARLISTINGS_FILE);
        } catch (IOException e) {
            quizPanel.setText("No Listings added yet");
        } catch (IndexOutOfBoundsException e) {
            quizPanel.setText("Please initialize listings file before proceeding");
        }
    }

    private void savePrompts() throws IOException{
        try {
            Writer writer = new Writer(new File(PROMPT_FILE));
            writer.write(prompts);
            writer.close();
            System.out.println("Quiz saved to file " + PROMPT_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Quiz to " + PROMPT_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Please load the file before trying to save it");
        }
    }

    public void returnToMainMenu() {
        mainMenu.setVisible(true);
        promptPanel.setVisible(false);
        quizPanel.setVisible(false);
    }

}



