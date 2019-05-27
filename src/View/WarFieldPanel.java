package View;

import javax.swing.*;
import java.awt.*;

public class WarFieldPanel extends JPanel {

    WarFieldPanel(){
      super(new GridLayout(5, 10));
      setBackground(new Color(0,0, 70));
      addFields();
    }

    public GridBagConstraints createWarFieldPanel(){
      GridBagConstraints c = new GridBagConstraints();
      c.gridy = 1;
      c.gridx = 1;
      c.gridwidth = 1;
      c.gridheight = 1;
      c.ipadx = 650;
      c.ipady = 450;
      return c;
    }

    private void addFields(){
      for(int i = 0; i < 50; ++i)
        add(new JButton("button"));
    }
}
