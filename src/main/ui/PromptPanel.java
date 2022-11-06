package ui;

import javax.swing.*;
import java.awt.*;

public class PromptPanel extends JPanel {
    private int width;
    private int height;
    private JTextField prompt;

    public PromptPanel() {
        this.setPreferredSize(new Dimension(40, 20));
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());

        prompt = new JTextField("");
        prompt.setBorder(BorderFactory.createEmptyBorder());
        prompt.setBackground(Color.lightGray);

        this.add(prompt, BorderLayout.CENTER);

    }


}
