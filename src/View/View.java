package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class View extends JFrame
{
  private final ImageIcon reaper = new ImageIcon(new ImageIcon(getClass().getResource("/REAPER.png")).getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
  private WarFieldPanel space;
  private BasePanel basePanel;
  private ScorePanel scorePanel;
  private ArrayList<JLabel> enemyPositions;
  private int current_target;
  private Controller gameHandler;


  public View(Controller controller){
    super("ME3 Relay Defence");
    space = new WarFieldPanel();
    basePanel = new BasePanel();
    gameHandler = controller;
    scorePanel = new ScorePanel(gameHandler.getBase());
    enemyPositions = new ArrayList<>();
    current_target = 0;
    setWindow();
  }

  private void setWindow(){
    EventQueue.invokeLater(() -> {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridBagLayout());

      space.setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.lightGray));
      getContentPane().add(space, space.createWarFieldPanel());
      space.setFocusable(true);

      basePanel.setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.lightGray));
      basePanel.setPreferredSize(new Dimension(150, 280));
      getContentPane().add(basePanel, basePanel.createBasePanel());

      scorePanel.setBorder(BorderFactory.createEtchedBorder(1));
      scorePanel.setPreferredSize(new Dimension(650, 40));
      getContentPane().add(scorePanel, scorePanel.createScorePanel());

      addFields();
      startMenu();

      pack();
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
    });
  }

  private void startMenu() {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    JMenu startUp = new JMenu("New game");
    menuBar.add(startUp);
    JMenuItem start = new JMenuItem("start");
    startUp.add(start);
    start.addActionListener((ActionEvent e) -> {
      enemyPositions.get(0).setBorder(BorderFactory.createEtchedBorder(1, Color.RED, Color.RED));
      space.addKeyListener(new TargetController());
      space.addKeyListener(new FireController());
      Thread game = new Thread(gameHandler.assaultBase);
      game.start();
    });
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
        label.setForeground(Color.WHITE);
        label.setFocusable(true);
        space.add(label, c);
        enemyPositions.add(label);
      }
    }
  }

  public void drawShip(int position){
    enemyPositions.get(position).setIcon(reaper);
  }

  public void clearShip(int position){
    enemyPositions.get(position).setIcon(null);
  }

  private class TargetController extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e) {
      enemyPositions.get(current_target).setBorder(BorderFactory.createEtchedBorder(1, Color.WHITE, Color.WHITE));

      if(e.getKeyCode() == KeyEvent.VK_RIGHT && current_target < enemyPositions.size() - 1) {
        current_target++;
      }
      else if(e.getKeyCode() == KeyEvent.VK_LEFT && current_target > 0){
        current_target--;
      }
      else if(e.getKeyCode() == KeyEvent.VK_UP && current_target - 5 >= 0) {
        current_target = current_target - 5;
      }
      else if(e.getKeyCode() == KeyEvent.VK_DOWN && current_target + 5 < enemyPositions.size()) {
        current_target = current_target + 5;
      }
      enemyPositions.get(current_target).setBorder(BorderFactory.createEtchedBorder(1, Color.RED, Color.RED));
    }
  }

  private class FireController extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_SPACE){
        //enemyPositions.get(current_target).setIcon(reaper);
        gameHandler.shotFired(current_target);
      }
      else if(e.getKeyCode() == KeyEvent.VK_ENTER){
        enemyPositions.get(current_target).setIcon(null);
      }
    }
  }
}