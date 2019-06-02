package View;

import javax.swing.*;
import java.awt.*;
import Model.Base;

public class ScorePanel extends JPanel {

  private Base target;
  private JLabel [] score_panels;
  private int current_score;

  ScorePanel(Base target){
    super(new GridLayout());
    this.target = target;
    current_score = 0;
    setBackground(Color.BLACK);
    score_panels = new JLabel[4];
    for(int i = 0; i < 4; ++i){
      score_panels[i] = new JLabel();
      score_panels[i].setPreferredSize(new Dimension(165, 40));
      add(score_panels[i]);
      score_panels[i].setBorder(BorderFactory.createEtchedBorder(1, Color.blue, Color.WHITE));
      score_panels[i].setFont(new Font(null, Font.PLAIN, 25));
      score_panels[i].setForeground(Color.WHITE);
    }
    score_panels[0].setText("HP: " + target.getHp());
    score_panels[1].setText("Score: " + current_score);
    target.setDisplayPanel(this);
  }

  public void displayHP(){
    score_panels[0].setText("HP: " + target.getHp());
  }

  void updateScore(){
    current_score = current_score + 10;
    score_panels[1].setText("Score: " + current_score);
    if(current_score % 50 == 0 )
      target.regenerate(25);
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
