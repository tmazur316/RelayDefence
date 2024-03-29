package Model;

import java.util.ArrayList;
import java.util.Random;

public class WarField {

  private final int width;
  private final ArrayList<Integer> free_rows;
  private final ArrayList<Integer> positions;

  public WarField(int x, int y) {
    width = x;

    free_rows = new ArrayList<>(y);
    for(int i = 0; i < y; i++) {
      free_rows.add(i);
    }

    positions = new ArrayList<>(y);
    for(int i = 0; i < width; i++) {
     positions.add(-1);
    }
  }

  public int findRow() {
    if(free_rows.size() == 0) {
      return -1;
    }
    Random rand = new Random();
    int new_row = rand.nextInt(1000) % free_rows.size();
    return free_rows.get(new_row);
  }

  public void saveRow(int number) {
    free_rows.remove((Integer)number);
  }

  public int saveColumn(int row) {
    Random rand = new Random();
    int new_column = rand.nextInt(1000) % width;
    positions.set(row, new_column);
    return new_column;
  }

  public void returnRow(int number) {
    free_rows.add(number);
    positions.set(number, -1);
  }

  public boolean checkRow(int number) {
    return !free_rows.contains(number);
  }

  public boolean checkColumn(int row, int column){
    return (positions.get(row) == column);
  }
}