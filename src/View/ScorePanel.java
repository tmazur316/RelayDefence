package View;

import javax.swing.*;
import java.awt.*;

import Controller.Controller;

public class ScorePanel extends JPanel {

  private Controller warHandler;
  private JLabel baseStatus;

  ScorePanel(Controller warHandler){
    super(new GridLayout());
    setBackground(Color.BLACK);
    this.warHandler = warHandler;
    JLabel hpString = new JLabel("HP:");
    hpString.setFont(new Font(null, Font.PLAIN, 25));
    hpString.setForeground(Color.WHITE);
    add(hpString);
    baseStatus = new JLabel();
    baseStatus.setText(String.valueOf(warHandler.getBaseHP()));
    baseStatus.setFont(new Font(null, Font.PLAIN, 25));
    baseStatus.setForeground(Color.WHITE);
    add(baseStatus);
  }


  public GridBagConstraints createScorePanel(){
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 2;
    c.gridx = 0;
    c.gridwidth = 2;
    c.gridheight = 1;
    return c;
  }
}
