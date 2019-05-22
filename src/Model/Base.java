package Model;

public class Base {
  private int hp;
  private final int max_hp;
  private final int firepower;
  private final String name;
  private final static Object lock = new Object();

  public Base(int h, int f, String n) {
    hp = max_hp = h;
    firepower = f;
    name = n;
  }

  public void takeDamage(int damage) {
    if(hp - damage <= 0) {
      hp = 0;
    }
    else hp -= damage;
  }

  public void regenerate(int health) {
    if(hp + health >= max_hp) {
      hp = max_hp;
    }
    else hp += health;
  }

  public void fire(Ship enemy) {
    enemy.destroyArmor(firepower);
  }

  public int getHp() {
    synchronized(lock) {
      return hp;
    }
  }
}
