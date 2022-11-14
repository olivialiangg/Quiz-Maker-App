package ui;

import model.Prompt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class GUI3 extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private JButton removeButton;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;

    private JTextField question;
    private JTextField answer;
    private JTextField difficulty;

    private Prompt examplePrompt;

    public GUI3() {
        super(new BorderLayout());

        listModel = new DefaultListModel<Prompt>();
        listModel.addElement("Example");

        initializeJList();

        JScrollPane listScrollPane = new JScrollPane(list);

        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.addActionListener(addListener);
        addButton.setActionCommand(addString);
        addButton.setEnabled(false);

        initializeButtons();

        question = new JTextField(10);
        initializeTextField(question, addListener);

        answer = new JTextField(10);
        initializeTextField(answer, addListener);

        difficulty = new JTextField(10);
        initializeTextField(difficulty, addListener);


        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        initializePanel(buttonPane);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    public void initializeButtons() {

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
//        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
//        loadButton.addActionListener(new LoadListener());
    }

    public void initializeJList() {
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(20);
    }

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


    public void initializeTextField(JTextField text, AddListener listener) {
        text.addActionListener(listener);
        text.getDocument().addDocumentListener(listener);
    }


    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever is selected.
            int index = list.getSelectedIndex();
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

    class SaveListeneer {

    }


    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String userQuestion = question.getText();
            String userAnswer = answer.getText();
            String userDifficulty = difficulty.getText();

            //User didn't type in a unique userQuestion...
            if (userQuestion.equals("") && (userAnswer.equals("") && (userDifficulty.equals("")))) {
                Toolkit.getDefaultToolkit().beep();
                question.requestFocusInWindow();
                question.selectAll();
                answer.requestFocusInWindow();
                answer.selectAll();
                difficulty.requestFocusInWindow();
                difficulty.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(question.getText() + answer.getText() + difficulty.getText(), index);
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
            resetTextField();
        }

        public void resetTextField() {
            question.requestFocusInWindow();
            question.setText("");
            answer.requestFocusInWindow();
            answer.setText("");
            difficulty.requestFocusInWindow();
            difficulty.setText("");
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Your Quiz");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUI3();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

