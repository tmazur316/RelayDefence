package Controller;

import Model.Base;
import Model.Ship;
import Model.WarField;
import Model.WarFieldHandler;
import View.View;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controller {

  private ArrayList<Ship> fleet;
  private WarFieldHandler handler;
  private final static Object lock = new Object();
  private View view;

  public Controller(){
    fleet = new ArrayList<>();
    Base defender = new Base(100, 2, "Citadel");
    WarField place = new WarField(5, 4);
    handler = new WarFieldHandler(place, defender);
    view = new View(this);
  }

  public Runnable assaultBase = () -> {
    while (handler.baseAlive())
    {
      Ship reinforcement = handler.setShip(2, 2);
      if (reinforcement == null) {
        continue;
      }
      view.drawShip(reinforcement.getRow() * 5 + reinforcement.getColumn());
      fleet.add(reinforcement);
      Thread assault = new Thread(reinforcement.siege);
      assault.start();
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        //obsluga wyjatku
      }
    }
  };

  public void shotFired(int position){
    int row = position/5;
    int column = position % 5;
    if(handler.checkTarget(row, column)){
      Ship target;
      for(Ship s: fleet){
        if(s.getRow() == row){
          target = s;
          target.destroyArmor(2);
          handler.removeShip(target);
          fleet.remove(target);
          view.clearShip(position);
          break;
        }
      }
    }
  }

  public int getBaseHP(){
    return handler.baseHP();
  }

  public Base getBase(){
    return handler.getBase();
  }

  public static void main(String [] args){
    Controller gameController = new Controller();
  }
}
