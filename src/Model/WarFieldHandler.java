package Model;

public class WarFieldHandler {

  private WarField space;

  public WarFieldHandler(){
    space = new WarField(8, 5);
  }

  public Ship setShip() {
    int row = space.findRow();
    if(row == -1){
      return null;
    }
    space.saveRow(row);
    int column = space.saveColumn(row);
    return new Ship(2, row, column, 1);
  }

  public void removeShip(Ship wreckage){
    space.returnRow(wreckage.getRow());
  }

  public boolean checkTarget(int row, int column){
    return (space.checkRow(row) && space.checkColumn(column));
  }
}
