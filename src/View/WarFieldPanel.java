package View;

import javax.swing.*;
import java.awt.*;

class WarFieldPanel extends JPanel {

  WarFieldPanel() {
    super(new GridBagLayout());
    setBackground(new Color(0, 0, 70));
  }

  GridBagConstraints createWarFieldPanel() {
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 1;
    int a;
    c.gridx = 1;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.fill = GridBagConstraints.BOTH;
    return c;
  }
}