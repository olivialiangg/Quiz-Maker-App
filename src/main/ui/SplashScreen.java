package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.JFrame;

// formatted code from http://www.java2s.com/Tutorials/Java/Swing_How_to/JWindow/
// Create_Swing_Splash_screen_with_progress_bar.htm

public class SplashScreen extends JWindow {

    private static JProgressBar progressBar = new JProgressBar();
    private int count = 1;
    private static int TIMER_PAUSE = 25;
    private static int PROGBAR_MAX = 100;
    static Timer progressBarTimer;


    ActionListener al = new ActionListener() {
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

    public SplashScreen() {
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

    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }

    public void addImageToLabel(JLabel l) {
        l.setIcon(new ImageIcon("./data/splashscreen.jpg"));
        l.setMinimumSize(new Dimension(20,20));
    }
}
