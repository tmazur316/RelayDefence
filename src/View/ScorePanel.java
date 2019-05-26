package View;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

  ScorePanel(){
    super(new GridLayout());
    setBackground(Color.BLACK);
  }

  public GridBagConstraints createScorePanel(){
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 2;
    c.gridx = 0;
    c.gridwidth = 2;
    c.gridheight = 1;
    c.ipadx = 800;
    c.ipady = 15;
    c.fill = GridBagConstraints.HORIZONTAL;
    return c;
  }
}
