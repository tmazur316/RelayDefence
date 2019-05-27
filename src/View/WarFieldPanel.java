package View;

import Model.Ship;

import javax.swing.*;
import java.awt.*;

public class WarFieldPanel extends JPanel {

  WarFieldPanel() {
    super(new GridBagLayout());
    setBackground(new Color(0, 0, 70));
    addFields();
  }

  public GridBagConstraints createWarFieldPanel() {
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 1;
    c.gridx = 1;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.fill = GridBagConstraints.BOTH;
    return c;
  }

  private void addFields() {
    GridBagConstraints c = new GridBagConstraints();
    c.gridwidth = 1;
    c.gridheight = 1;
    for(int i = 0; i < 4; i++) {
      c.gridy = i;
      for (int j = 0; j < 5; j++) {
        c.gridx = j;
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(100, 70));
        label.setBorder(BorderFactory.createEtchedBorder(1, new Color(207, 250, 255), new Color(207, 250, 255)));
        add(label, c);
      }
    }
  }
}