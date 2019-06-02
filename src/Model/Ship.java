package Model;

import java.util.concurrent.TimeUnit;

public class Ship {

  private int firepower;
  private final int row;
  private final int column;
  private int armor;
  private Base target;
  private static final Object lock = new Object();

  Ship(int gun, int y, int x, int a, Base defender) {
    firepower = gun;
    row = y;
    column = x;
    armor = a;
    target = defender;
  }

  private void attack() {
    synchronized(lock) {
      target.takeDamage(firepower);
    }
  }

   public void destroyArmor(int damage) {
    if (armor - damage <= 0)
      armor = 0;
    else armor -= damage;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public Runnable siege = () -> {
    while(armor > 0) {
      try{
        TimeUnit.SECONDS.sleep(1);
        this.attack();
      }
      catch (InterruptedException e) {
        System.out.println("Exception");
      }
    }
  };
}