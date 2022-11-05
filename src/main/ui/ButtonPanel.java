package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton addPrompt;
    private JButton viewHardPrompts;
    private JButton saveQuiz;
    private JButton loadQuiz;
    private Border border;

    public ButtonPanel() {

        this.setPreferredSize(new Dimension(300, 80));
        this.setBackground(Color.orange);

        border = BorderFactory.createEmptyBorder();

        addPrompt = new JButton("Add prompt");
        addPrompt.setBorder(border);
        addPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(addPrompt);

        this.add((Box.createHorizontalStrut(15)));
        viewHardPrompts = new JButton("Hard prompts");
        viewHardPrompts.setBorder(border);
        viewHardPrompts.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(viewHardPrompts);

        this.add((Box.createHorizontalStrut(15)));
        saveQuiz = new JButton("Save");
        saveQuiz.setBorder(border);
        saveQuiz.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(saveQuiz);

        this.add((Box.createHorizontalStrut(15)));
        loadQuiz = new JButton("Load");
        loadQuiz.setBorder(border);
        loadQuiz.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(loadQuiz);
    }

    public JButton getAddPrompt() {
        return addPrompt;
    }

    public JButton getViewHardPrompts() {
        return viewHardPrompts;
    }

    public JButton getSaveQuiz() {
        return saveQuiz;
    }

    public JButton getLoadQuiz() {
        return loadQuiz;
    }
}