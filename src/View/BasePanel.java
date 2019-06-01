package View;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JLabel {

  private final ImageIcon base = new ImageIcon(new ImageIcon(getClass().getResource("/citadel")).getImage().getScaledInstance(150, 280, Image.SCALE_DEFAULT));

  BasePanel(){
    super();
    setIcon(base);
  }

  public GridBagConstraints createBasePanel(){
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 1;
    c.gridx = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c. fill = GridBagConstraints.VERTICAL;
    return c;
  }
}
