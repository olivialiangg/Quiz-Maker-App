package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new SplashScreen();
        Thread.sleep(3700);
        new GUI();
    }
}

//        try {
//            new QuizApp();
//            new GUI();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
//    }
//}
