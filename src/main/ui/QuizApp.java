package ui;

import model.PromptList;
import model.Prompt;

import java.util.Scanner;


public class QuizApp {
    private PromptList promptList;
    private String question;
    private String answer;
    private Scanner input;

    // EFFECTS: runs the teller application
    public QuizApp() {
        runQuiz();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runQuiz() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addAPrompt();
        } else if (command.equals("r")) {
            removeAPrompt();
       // } else if (command.equals("t")) {
       //     viewAllPrompts();
        } else if (command.equals("v")) {
            viewNumberOfPrompts();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes prompt list
    private void initialize() {
        promptList = new PromptList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\tr -> remove");
        System.out.println("\tt -> total");
        //System.out.println("\tv -> view");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a new prompt
    private void addAPrompt() {
        System.out.println("\nProvide question:");
        question = input.nextLine();

        System.out.println("\nProvide answer:");
        answer = input.nextLine();
        Prompt p = new Prompt(question, answer);

        promptList.addPrompt(p);
    }


    // MODIFIES: this
    // EFFECTS: removes given prompt
    private void removeAPrompt() {
        System.out.println("\nProvide question:");
        question = input.nextLine();

        System.out.println("\nProvide answer:");
        answer = input.nextLine();
        Prompt p = new Prompt(question, answer);

        if (promptList.containsPrompt(p)) {
            promptList.removePrompt(p);
        }
    }

    // EFFECTS: returns number of prompts in list
    private void viewNumberOfPrompts() {
        System.out.println(promptList.getSize());
    }
/*
    //EFFECTS: prints all prompts in list
    private void viewAllPrompts() {
        if (promptList.getSize() > 0) {
            for (Prompt p : promptList) {
                System.out.println(p.getQuestion() + " " + p.getAnswer());
            }
        }
        System.out.println("");

 */
}





