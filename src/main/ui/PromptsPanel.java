package ui;

import javax.swing.*;
import java.awt.*;

public class PromptsPanel extends JPanel {

    public PromptsPanel() {
        GridLayout gridLayout = new GridLayout(15, 1);
        gridLayout.setVgap(5);

        this.setLayout(gridLayout);
    }
}
