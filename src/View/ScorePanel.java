package View;

import javax.swing.*;
import java.awt.*;
import Model.Base;

public class ScorePanel extends JPanel {

  private Base target;
  private JLabel baseStatus;

  ScorePanel(Base target){
    super(new GridLayout());
    this.target = target;
    setBackground(Color.BLACK);
    JLabel hpString = new JLabel("HP:");
    hpString.setFont(new Font(null, Font.PLAIN, 25));
    hpString.setForeground(Color.WHITE);
    add(hpString);
    baseStatus = new JLabel();
    baseStatus.setFont(new Font(null, Font.PLAIN, 25));
    baseStatus.setForeground(Color.WHITE);
    baseStatus.setText(String.valueOf(target.getHp()));
    add(baseStatus);
    target.setDisplayPanel(this);
  }

  public void displayHP(){
    baseStatus.setText(null);
    baseStatus.setText(String.valueOf(target.getHp()));
  }

  GridBagConstraints createScorePanel(){
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 2;
    c.gridx = 0;
    c.gridwidth = 2;
    c.gridheight = 1;
    return c;
  }
}
