package Model;

import java.util.ArrayList;
import java.util.Random;

public class WarField {

  private final int height;
  private final int width;
  private ArrayList<Integer> free_rows;
  private ArrayList<Integer> positions;

  public WarField(int x, int y) {
    height = y;
    width = x;

    free_rows = new ArrayList<>(height);
    for(int i = 0; i < height; i++) {
      free_rows.add(i);
    }

    positions = new ArrayList<>(width);
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
    return free_rows.contains(number);
  }

  public boolean checkColumn(int number){
    return (positions.get(number) != -1);
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}