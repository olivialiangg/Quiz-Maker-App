package ui;

import javax.swing.*;
import java.awt.*;

public class PromptPanel extends JPanel {
    private int width;
    private int height;
    private JTextField prompt;
//    private JTextField question;
//    private JTextField answer;
//    private JTextField difficulty;

    public PromptPanel() {
        this.setPreferredSize(new Dimension(40, 20));
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());

        prompt = new JTextField("");
        prompt.setBorder(BorderFactory.createEmptyBorder());
        prompt.setBackground(Color.lightGray);

//        question = new JTextField("");
//        prompt.setBorder(BorderFactory.createEmptyBorder());
//        prompt.setBackground(Color.lightGray);
//
//        answer = new JTextField("");
//        prompt.setBorder(BorderFactory.createEmptyBorder());
//        prompt.setBackground(Color.lightGray);
//
//        difficulty = new JTextField("");
//        prompt.setBorder(BorderFactory.createEmptyBorder());
//        prompt.setBackground(Color.lightGray);

        this.add(prompt, BorderLayout.CENTER);
    }

}
