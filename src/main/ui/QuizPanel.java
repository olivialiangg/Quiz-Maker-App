package ui;

import model.Prompt;
import model.PromptList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuizPanel extends JFrame {

    private TitleBar title;
    private ButtonPanel button;
    private PromptsPanel prompts;

    private JButton addPrompt;
    private JButton viewHardPrompts;
    private JButton saveQuiz;
    private JButton loadQuiz;

    private int width;
    private int height;

    private Prompt prompt;
    private PromptList quiz;

    public QuizPanel() {

        width = 500;
        height = 700;

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        title = new TitleBar();
        button = new ButtonPanel();
        prompts = new PromptsPanel();


        this.add(title, BorderLayout.NORTH);
        this.add(button, BorderLayout.SOUTH);
        this.add(prompts, BorderLayout.CENTER);

        addPrompt = button.getAddPrompt();
        viewHardPrompts = button.getRemovePrompt();
        saveQuiz = button.getSaveQuiz();
        loadQuiz = button.getLoadQuiz();

        setUpButtonListeners();

    }

    public void setUpButtonListeners() {

        addPrompt.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PromptPanel prompt = new PromptPanel();
                prompts.add(prompt);
                revalidate();
            }
        }
        );

        viewHardPrompts.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PromptPanel prompt = new PromptPanel();
                prompts.add(prompt);
                revalidate();
            }
        }
        );


    }
}