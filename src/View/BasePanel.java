package View;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {

  BasePanel(){
    super(new GridLayout());
    setBackground(new Color(0, 0, 70));
  }

  public GridBagConstraints createBasePanel(){
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 1;
    c.gridx = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.ipadx = 150;
    c.ipady = 450;
    return c;
  }
}
