package ui;

import javax.swing.*;
import java.awt.*;

public class QuizPanel extends JFrame  {

    private TitleBar title;
    private ButtonPanel button;
    private PromptsPanel prompts;

    private JButton addPrompt;
    private JButton viewHardPrompts;
    private JButton saveQuiz;
    private JButton loadQuiz;

    public QuizPanel() {

        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        title = new TitleBar();
        button = new ButtonPanel();


        this.add(title, BorderLayout.NORTH);
        this.add(button, BorderLayout.SOUTH);
    }
//        prompts = new PromptsPanel();
//        button = new ButtonPanel();
//
//        this.add(title, BorderLayout.NORTH);
//        this.add(button, BorderLayout.SOUTH);
//        this.add(prompts, BorderLayout.CENTER);



}
