package Model;

public class WarFieldHandler {

  private final WarField space;
  private final Base defender;

  public WarFieldHandler(WarField warfield, Base base){
    space = warfield;
    defender = base;
  }

  public Ship setShip(int firepower, int armor) {
    int row = space.findRow();
    if(row == -1){
      return null;
    }
    space.saveRow(row);
    int column = space.saveColumn(row);
    return new Ship(firepower, row, column, armor, defender);
  }

  public void removeShip(Ship wreckage){
    space.returnRow(wreckage.getRow());
  }

  public boolean checkTarget(int row, int column){
    return (space.checkRow(row) && space.checkColumn(row, column));
  }

  public Base getBase(){
    return defender;
  }
}
