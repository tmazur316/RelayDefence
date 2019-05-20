package Model;

public class Ship {

  private final int firepower;
  private final int row;
  private final int column;
  private int armor;

  public Ship(int gun, int y, int x, int a) {
    firepower = gun;
    row = y;
    column = x;
    armor = a;
  }

  public void attack(Base target) {
    target.takeDamage(firepower);
  }

  public void destroyArmor(int damage) {
    if(armor - damage <= 0)
      armor = 0;
    else armor -= damage;
  }

  public boolean idenitifyShip(int y, int x){
    return (y == row && x == column);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public int getArmor() {
    return armor;
  }
}
