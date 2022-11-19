package ui;

import model.Prompt;
import model.PromptList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.event.*;


public class GUI extends JFrame implements ListSelectionListener {
    private JList list;
    private PromptList prompts;
    private DefaultListModel listModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private static final String PROMPT_FILE = "./data/promptlist.json";

    private JButton removeButton;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;

    private JTextField question;
    private JTextField answer;
    private JTextField difficulty;

    // EFFECTS: sets up GUI
    public GUI() {

        super("Your Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 700));
        pack();
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<Prompt>();

        initializeJList();
        prompts = new PromptList("Your Quiz");

        JScrollPane listScrollPane = new JScrollPane(list);



        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.addActionListener(addListener);


        initializeButtons();

        question = new JTextField(10);
        answer = new JTextField(10);
        difficulty = new JTextField(10);
        initializeTextField(question, answer, difficulty, addListener);


        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        initializePanel(buttonPane);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        setVisible(true);
    }

    // MODIFIES: addButton, removeButton, saveButton, loadButtons
    // EFFECTS: sets name for buttons and its corresponding actions
    public void initializeButtons() {

        addButton.setActionCommand(addString);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());
    }

    // EFFECTS: create a jlist
    public void initializeJList() {
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(20);
    }


    // EFFECTS: creates a panel with text fields
    public void initializePanel(JPanel panel) {
        panel.add(Box.createHorizontalStrut(5));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(Box.createHorizontalStrut(5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(question);
        panel.add(answer);
        panel.add(difficulty);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(saveButton);
        panel.add(loadButton);

    }


    // EFFECTS: creates text fields and adds a listener for them
    public void initializeTextField(JTextField text1, JTextField text2, JTextField text3, AddListener listener) {
        text1.addActionListener(listener);
        text1.getDocument().addDocumentListener(listener);

        text2.addActionListener(listener);
        text2.getDocument().addDocumentListener(listener);

        text3.addActionListener(listener);
        text3.getDocument().addDocumentListener(listener);

    }

    class SaveListener implements ActionListener {


        // EFFECTS: saves the prompt list to file
        public void actionPerformed(ActionEvent e) {
            JsonWriter writer = new JsonWriter(PROMPT_FILE);
            try {
                writer.open();
                writer.write(prompts);
                writer.close();
            } catch (FileNotFoundException exception) {
                System.out.println("File not found.");
            }
        }
    }


    class LoadListener implements ActionListener {

        // MODIFIES: prompts
        // EFFECTS: loads prompt list from file
        public void actionPerformed(ActionEvent e) {
            try {
                JsonReader reader = new JsonReader(PROMPT_FILE);
                prompts = reader.read();

                for (Prompt p : prompts.getPromptList()) {
                    listModel.insertElementAt(prompts.viewPrompts(p), 0);
                }

            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + PROMPT_FILE);
            }
        }

    }


    class RemoveListener implements ActionListener {

        // MODIFIES: prompts
        // EFFECTS: loads prompt list from file
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            System.out.println(listModel.getElementAt(index));

            listModel.remove(index);
            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

//        public void actionPerformed(ActionEvent e) {
//            String userQuestion = question.getText();
//            String userAnswer = answer.getText();
//            String userDifficulty = difficulty.getText();
//
//            if (userQuestion.equals("") || (userAnswer.equals("") || (userDifficulty.equals("")))) {
//                question.requestFocusInWindow();
//                question.selectAll();
//                answer.requestFocusInWindow();
//                answer.selectAll();
//                difficulty.requestFocusInWindow();
//                difficulty.selectAll();
//            }
//
//            Prompt p = new Prompt(difficulty.getText(), answer.getText(), Boolean.valueOf(difficulty.getText()));
//            prompts.removePrompt(p);
//        }



    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        private String difficultylvl;

        // MODIFIES: button
        // EFFECTS: sets button to parameter button
        public AddListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: prompts
        // EFFECTS: adds inputted prompt from text field to prompts
        public void actionPerformed(ActionEvent e) {
            String userQuestion = question.getText();
            String userAnswer = answer.getText();
            String userDifficulty = difficulty.getText();

            if (userQuestion.equals("") || (userAnswer.equals("") || (userDifficulty.equals("")))) {
                question.requestFocusInWindow();
                question.selectAll();
                answer.requestFocusInWindow();
                answer.selectAll();
                difficulty.requestFocusInWindow();
                difficulty.selectAll();
            }

            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            getDifficulty(difficulty.getText());

            listModel.insertElementAt(question.getText() + " " + answer.getText() + "  " + difficultylvl, index);
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

            prompts.addPrompt(new Prompt(question.getText(), answer.getText(), Boolean.valueOf(difficulty.getText())));

            resetTextField();
        }

        // MODIFIES: difficultylvl
        // EFFECT: sets difficultylvl to "hard" if user put true for difficulty
        public void getDifficulty(String s) {

            if (Boolean.valueOf(s)) {
                difficultylvl = "hard";
            } else {
                difficultylvl = "";
            }
        }

        // MODIFIES: question, answer, difficulty
        // EFFECTS: sets all text fields empty strings
        public void resetTextField() {
            question.requestFocusInWindow();
            question.setText("");
            answer.requestFocusInWindow();
            answer.setText("");
            difficulty.requestFocusInWindow();
            difficulty.setText("");
        }



        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }


        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }


        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }

}