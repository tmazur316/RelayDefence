package View;

import javax.swing.*;
import java.awt.*;
import Model.Base;

public class ScorePanel extends JPanel {

  private Base target;
  private JLabel [] score_panels;
  private int current_score;
  private int next_wave_time;
  private int seconds;
  private int minutes;

  ScorePanel(Base target){
    super(new GridLayout());
    this.target = target;
    current_score = seconds = minutes = 0;
    next_wave_time = 30;
    setBackground(Color.BLACK);
    score_panels = new JLabel[4];
    for(int i = 0; i < 4; ++i){
      score_panels[i] = new JLabel();
      score_panels[i].setPreferredSize(new Dimension(165, 40));
      add(score_panels[i]);
      score_panels[i].setFont(new Font(null, Font.PLAIN, 25));
      score_panels[i].setForeground(Color.WHITE);
    }
    score_panels[0].setText("HP: " + target.getHp());
    score_panels[1].setText("Score: " + current_score);
    score_panels[2].setText("Time: " + "00" + ":" + "00");
    score_panels[3].setText("Wave: 00:" + next_wave_time);
    target.setDisplayPanel(this);
  }

  public void displayHP(){
    score_panels[0].setText("HP: " + target.getHp());
  }

  void updateScore(){
    current_score += 10;
    score_panels[1].setText("Score: " + current_score);
    if(current_score % 50 == 0 )
      target.regenerate(10);
  }

  void resetPanels() {
    current_score = 0;
    next_wave_time = 30;
    seconds = 0;
    minutes = 0;
    score_panels[0].setText("HP: " + target.getHp());
    score_panels[1].setText("Score: " + current_score);
    score_panels[2].setText("Time: " + "00" + ":" + "00");
    score_panels[3].setText("Wave: 00:" + next_wave_time);
  }

  public void displayClock(){
    seconds++;
    if(seconds == 60){
      minutes++;
      seconds = 0;
    }
    score_panels[2].setText("Time: " + minutes + ":" + seconds);
  }

  public void nextWaveClock(){
    next_wave_time--;
    if(next_wave_time < 0){
      next_wave_time = 30;
    }
    score_panels[3].setText("Wave: 00:" + next_wave_time);
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
