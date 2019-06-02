package Model;

import View.ScorePanel;

import javax.swing.*;

public class Base {
  private volatile int hp;
  private final int max_hp;
  private final int firepower;
  private final String name;
  private final static Object lock = new Object();
  private final static Object lock1 = new Object();
  private ScorePanel displayPanel;

  public Base(int hp, int firepower, String name) {
    this.hp = this.max_hp = hp;
    this.firepower = firepower;
    this.name = name;
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

  public void fire(Ship enemy) {
    enemy.destroyArmor(firepower);
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
