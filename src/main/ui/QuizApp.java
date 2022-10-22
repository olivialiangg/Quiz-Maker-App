package ui;

import model.PromptList;
import model.Prompt;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class QuizApp {
    private PromptList promptList;
    private Scanner input;
    private static final String JSON_STORE = "./data/promptlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Used TellerApp.java to help me set up my runQuiz() processCommand(), initialize(), and displayMenu()

    // EFFECTS: runs the quiz application and throws FileNotFoundException {
    public QuizApp() throws FileNotFoundException {
        promptList = new PromptList("Your quiz");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nEnd");
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
        } else if (command.equals("v")) {
            viewAllPrompts();
        } else if (command.equals("h")) {
            viewNumberOfHardPrompts();
        } else if (command.equals("s")) {
            savePromptList();
        } else if (command.equals("l")) {
            loadPromptList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes prompt list
    private void initialize() {
        promptList = new PromptList("Your quiz");
        input = new Scanner(System.in);

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a prompt");
        System.out.println("\tr -> remove a prompt");
        System.out.println("\tt -> total number of prompts");
        System.out.println("\tv -> view all prompts");
        System.out.println("\th -> total number of hard prompts");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\te -> exit");
    }

    // MODIFIES: this
    // EFFECTS: conducts prompt addition to list
    private void addAPrompt() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nProvide question:");
        String question = scanner.nextLine();

        System.out.println("\nProvide answer:");
        String answer = scanner.nextLine();

        System.out.println("\nProvide true for hard or false for easy:");
        Boolean hard = scanner.nextBoolean();


        Prompt p = new Prompt(question, answer, hard);
        promptList.addPrompt(p);
    }

    // MODIFIES: this
    // EFFECTS: conducts prompt removal from list
    private void removeAPrompt() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nProvide question to remove:");
        String question = scanner.nextLine();

        System.out.println("\nProvide answer to remove:");
        String answer = scanner.nextLine();

        System.out.println("\nProvide true for hard or false for easy:");
        Boolean hard = scanner.nextBoolean();

        Prompt p = new Prompt(question, answer, hard);
        promptList.removePrompt(p);
    }

    // EFFECTS: prints number of prompts in list
    private void viewNumberOfPrompts() {
        System.out.println("\nYou have " + promptList.totalPrompts() + " questions and answers in your quiz");
    }

    // EFFECTS: prints out all questions and answers in list
    private void viewAllPrompts() {
        for (Prompt p : promptList.getPromptList()) {
            System.out.println(promptList.viewPrompts(p));
        }
    }

    // EFFECTS: prints number of hard prompts in list
    private void viewNumberOfHardPrompts() {
        System.out.println("\nYou have " + promptList.totalHardPrompts() + " hard questions and answers in your quiz");
    }

    // EFFECTS: saves the prompt list to file
    private void savePromptList() {
        try {
            jsonWriter.open();
            jsonWriter.write(promptList);
            jsonWriter.close();
            System.out.println("Saved " + promptList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPromptList() {
        try {
            promptList = jsonReader.read();
            System.out.println("Loaded  " + promptList.getName() + "  from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}





