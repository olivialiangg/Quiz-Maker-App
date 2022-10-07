package ui;

import model.PromptList;
import model.Prompt;

import java.util.Scanner;


public class QuizApp {
    private PromptList promptList;
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
        } else if (command.equals("t")) {
            viewNumberOfPrompts();
        //} else if (command.equals("v")) {
        //    viewAllPrompts();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes prompt list
    private void initialize() {
        promptList = new PromptList();
        input = new Scanner(System.in);

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

    private void addAPrompt() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nProvide question:");
        String question = scan.nextLine();

        System.out.println("\nProvide answer:");
        String answer = scan.nextLine();

        Prompt p = new Prompt(question, answer);
        promptList.addPrompt(p);
    }


    // MODIFIES: this
    // EFFECTS: removes given prompt
    private void removeAPrompt() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nProvide question to remove:");
        String question = scan.nextLine();

        System.out.println("\nProvide answer to remove:");
        String answer = scan.nextLine();

        Prompt p = new Prompt(question, answer);

        promptList.removePrompt(p);
    }


    // EFFECTS: returns number of prompts in list
    private void viewNumberOfPrompts() {
        System.out.println("\nNumber of prompts in your quiz " + promptList.getSize() );
    }

//
//    //EFFECTS: prints out all prompts in list
//    private void viewAllPrompts() {
//    for (Prompt p: promptList) {
//        System.out.println(viewAllPrompts(p));
//        }
//    }
}




