package View;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame
{

  public View(){
    super("ME3 Relay Defence");
  }

  private void setWindow(){
    EventQueue.invokeLater(() -> {
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      JPanel mainPanel = new JPanel(new GridBagLayout());
      getContentPane().add(mainPanel);

      WarFieldPanel space = new WarFieldPanel();
      space.setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.lightGray));
      mainPanel.add(space, space.createWarFieldPanel());

      MenuPanel choicePanel = new MenuPanel();
      choicePanel.setBorder(BorderFactory.createEtchedBorder(1));
      mainPanel.add(choicePanel, choicePanel.createMenuPanel());

      BasePanel basePanel = new BasePanel();
      basePanel.setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.lightGray));
      mainPanel.add(basePanel, basePanel.createBasePanel());

      ScorePanel scorePanel = new ScorePanel();
      scorePanel.setBorder(BorderFactory.createEtchedBorder(1));
      mainPanel.add(scorePanel, scorePanel.createScorePanel());

      pack();
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
    });
  }

  public static void main(String [] args){
    View view = new View();
    view.setWindow();
  }
}