package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View extends JFrame
{

  public View(){
    super("ME3 Relay Defence");
  }

  private void setWindow(){
    EventQueue.invokeLater(() -> {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridBagLayout());

      WarFieldPanel space = new WarFieldPanel();
      space.setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.lightGray));
      //space.setPreferredSize(new Dimension(500, 280));
      getContentPane().add(space, space.createWarFieldPanel());

      BasePanel basePanel = new BasePanel();
      basePanel.setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.lightGray));
      basePanel.setPreferredSize(new Dimension(150, 280));
      getContentPane().add(basePanel, basePanel.createBasePanel());

      ScorePanel scorePanel = new ScorePanel();
      scorePanel.setBorder(BorderFactory.createEtchedBorder(1));
      scorePanel.setPreferredSize(new Dimension(650, 20));
      getContentPane().add(scorePanel, scorePanel.createScorePanel());

      startMenu();

      pack();
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
    });
  }

  private void startMenu(){
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    JMenu startUp = new JMenu("New game");
    menuBar.add(startUp);
    JMenuItem start = new JMenuItem("start");
    startUp.add(start);
  }


  public static void main(String [] args){
    View view = new View();
    view.setWindow();
  }
}