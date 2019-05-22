package View;

import Controler.Controler;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame
{
  private Controler control;

  public View(){
    control = new Controler();
  }

  private void setWindow(){

    EventQueue.invokeLater(() -> {
      JFrame frame = new JFrame("okno");
      JButton cannon = new JButton("fire");
      frame.setSize(400, 400);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setResizable(true);
      frame.add(cannon);
      cannon.addActionListener((actionEvent)-> control.assaultBase());
      frame.setVisible(true);
    });
  }

  public static void main(String [] args){
    View view = new View();
    view.setWindow();
  }

}