package ui;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {

    public TitleBar() {
        this.setPreferredSize(new Dimension(500, 100));

        JLabel titleText = new JLabel("Your Quiz");
        titleText.setPreferredSize(new Dimension(250, 50));
        titleText.setFont(new Font("Arial",Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleText);
    }

}
