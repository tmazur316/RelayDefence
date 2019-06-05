package Model;

import javax.swing.Timer;

public class Ship {

  private int firepower;
  private final int row;
  private final int column;
  private int armor;
  private Base target;
  private Timer nextShotTimer;

  Ship(int gun, int y, int x, int a, Base defender) {
    firepower = gun;
    row = y;
    column = x;
    armor = a;
    target = defender;
    nextShotTimer = null;
  }

  private void attack() {
      target.takeDamage(firepower);
  }

  public void destroyArmor(int damage) {
    if (armor - damage < 0)
      armor = 0;
    else armor -= damage;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  private void nextShot(){
    nextShotTimer.addActionListener((actionEvent) -> this.attack());
  }

  public void startTimer(){
    nextShotTimer = new javax.swing.Timer(1000, null);
    nextShot();
    nextShotTimer.setInitialDelay(0);
    nextShotTimer.start();
  }

  public void stopTimer(){
    if(nextShotTimer != null){
      nextShotTimer.stop();
    }
    nextShotTimer = null;
  }

  public Timer getTimer(){
    return this.nextShotTimer;
  }
}