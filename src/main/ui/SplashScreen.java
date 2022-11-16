package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.JFrame;

// formatted code from http://www.java2s.com/Tutorials/Java/Swing_How_to/JWindow/
// Create_Swing_Splash_screen_with_progress_bar.htm

public class SplashScreen extends JFrame {

    private static JProgressBar progressBar = new JProgressBar();
    private int count = 1;
    private static int TIMER_PAUSE = 25;
    private static int PROGBAR_MAX = 100;
    static Timer progressBarTimer;


    ActionListener al = new ActionListener() {

        // MODIFIES: progressBar
        // EFFECTS: constructs how long progress bar will take to run down
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            if (PROGBAR_MAX == count) {
                progressBarTimer.stop();
                SplashScreen.this.setVisible(false);
            }
            count++;
        }
    };

    // EFFECTS: sets up screen that image will be displayed on
    public SplashScreen() {

        setLocationRelativeTo(null);

        Container container = getContentPane();
        setPreferredSize(new Dimension(800, 500));

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder());
        container.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel();
        addImageToLabel(label);
        panel.add(label);

        progressBar.setMaximum(PROGBAR_MAX);
        container.add(progressBar, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        startProgressBar();
    }

    // EFFECTS: constructs progress bar
    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }

    // MODIFIES: label
    // EFFECTS: add an image to the label
    public void addImageToLabel(JLabel l) {
        l.setIcon(new ImageIcon("./data/splashscreen.jpg"));
        l.setMinimumSize(new Dimension(20,20));
    }

}
