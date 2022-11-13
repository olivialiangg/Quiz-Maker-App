package ui;

import model.Prompt;
import model.PromptList;

import javax.swing.*;
import java.util.*;
import javax.swing.JList;

public class JListPrompts {

    public static final int NEW_ELEMENT_IDX = 0;
    private final JList<Prompt> jlist;
    private List<Prompt> prompts;

    public JListPrompts() {
        prompts = new ArrayList<>();
        jlist = new JList<>();


        prompts.forEach(Prompt -> addPrompt(Prompt));

        // Set selection mode
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);;

    }

    public void addPrompt(Prompt p) {
        ((DefaultListModel<Prompt>) jlist.getModel()).add(NEW_ELEMENT_IDX,
                p);
    }

    public void removePrompt(Prompt p) {
        ((DefaultListModel<Prompt>) jlist.getModel()).removeElement(p);
    }
}


