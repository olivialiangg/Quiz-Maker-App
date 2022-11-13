package ui;

import com.sun.jdi.BooleanValue;
import model.Prompt;
import model.PromptList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI2 {

    public static final Prompt[] LIST_DATA = {};
    private JTextField questionField;
    private JTextField answerField;
    private JTextField difficultyField;
    private JButton addButton;
    private JButton deleteButton;
    private JList swingJList;
    private

        public GUI2() throws Exception {

            JFrame frame = new JFrame("Your Quiz");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create JList with a List of String names
            swingJList = new JList();

            questionField = new JTextField(26);
            answerField = new JTextField(26);
            difficultyField = new JTextField(26);

            // Adding a list selection listener
            swingJList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        Prompt p = swingJList.getSelectedValue();
                        String selectedQuestion = p.getQuestion();
                        String selectedAnswer = p.getAnswer();
                        String selectedDifficulty = String.valueOf(p.getIsHard());
                        questionField.setText(selectedQuestion);
                        answerField.setText(selectedAnswer);
                        difficultyField.setText(selectedDifficulty);
                    }
                }
            });

            // Create an action listener to add a new item to the List
            addButton = new JButton("Add Employee");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = questionField.getText();
                    if (name != null && !"".equals(name)) {
                        swingJList.addPrompt(name);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Employee name is empty", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Create an action listener to remove existed item from the List
            deleteButton = new JButton("Delete Employee");
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = questionField.getText();
                    if (name != null && !"".equals(name)) {
                        swingJList.removePrompt(name);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please, select employee name from the list",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            // Put the JList in a JScrollPane to handle scrolling
            JScrollPane listScrollPane = new JScrollPane();
            listScrollPane.setPreferredSize(new Dimension(250, 200));

            listScrollPane.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Prompts List",
                    TitledBorder.CENTER, TitledBorder.TOP));

            // Create a control panel
            JPanel buttonPane = new JPanel();
            buttonPane.add(questionField);
            buttonPane.add(addButton);
            buttonPane.add(deleteButton);

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    listScrollPane, buttonPane);
            splitPane.setDividerLocation(250);
            splitPane.setEnabled(false);

            frame.add(splitPane);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }
    }
