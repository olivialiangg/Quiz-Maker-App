package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    private JButton addPrompt;
    private JButton removePrompt;
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
        addPrompt.addActionListener(this);
        addPrompt.setActionCommand("Add prompt");
        this.add(addPrompt);

        this.add((Box.createHorizontalStrut(15)));
        removePrompt = new JButton("View hard prompts");
        removePrompt.setBorder(border);
        removePrompt.setFont(new Font("Arial", Font.BOLD, 20));
        removePrompt.addActionListener(this);
        addPrompt.setActionCommand("Add prompt");
        this.add(removePrompt);

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

    public JButton getRemovePrompt() {
        return removePrompt;
    }

    public JButton getSaveQuiz() {
        return saveQuiz;
    }

    public JButton getLoadQuiz() {
        return loadQuiz;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}