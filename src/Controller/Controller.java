package Controller;

import Model.Base;
import Model.Ship;
import Model.WarField;
import Model.WarFieldHandler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controller {

  private ArrayList<Ship> fleet;
  private WarFieldHandler handler;
  private final static Object lock = new Object();

  public Controller(){
    fleet = new ArrayList<>();
    Base defender = new Base(10, 2, "Citadel");
    WarField place = new WarField(5, 3);
    handler = new WarFieldHandler(place, defender);
  }

  public void assaultBase() {
    while(handler.baseAlive()){
      Ship reinforcement = handler.setShip(2, 2);
      if(reinforcement == null) {
        System.out.println("w");
        continue;
      }
      System.out.println("Nowy");
      fleet.add(reinforcement);
      Thread assault = new Thread(reinforcement.siege);
      assault.start();
      try{
        TimeUnit.SECONDS.sleep(1);
      }
      catch(InterruptedException e){
        //obsluga wyjatku
     }
    }
    System.out.println("Za");
  }
}
