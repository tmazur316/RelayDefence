package Model;

import View.ScorePanel;

public class Base {
  private volatile int hp;
  private final int max_hp;
  private final static Object lock = new Object();
  private ScorePanel displayPanel;

  public Base(int hp) {
    this.hp = this.max_hp = hp;
    displayPanel = null;
  }

  void takeDamage(int damage) {
    synchronized(lock){
      if(hp - damage <= 0) {
      hp = 0;
    }
    else hp -= damage;
    displayPanel.displayHP();
    }
  }

  public void regenerate(int health) {
    synchronized (lock) {
      if (hp + health >= max_hp) {
        hp = max_hp;
      } else hp += health;
      displayPanel.displayHP();
    }
  }

  public int getHp() {
    synchronized(lock) {
      return hp;
    }
  }

  public void setDisplayPanel(ScorePanel panel){
    displayPanel = panel;
  }
}