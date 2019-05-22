package Controler;

import Model.Base;
import Model.Ship;
import Model.WarField;
import Model.WarFieldHandler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controler {

  private ArrayList<Ship> fleet;
  private WarFieldHandler handler;
  private final static Object lock = new Object();

  public Controler(){
    fleet = new ArrayList<>();
    Base defender = new Base(10, 2, "Citadel");
    WarField place = new WarField(10, 5);
    handler = new WarFieldHandler(place, defender);
  }

  public void assaultBase(){
    while(handler.baseAlive()){
      Ship reinforcement = handler.setShip(2, 2);
      if(reinforcement == null){
        //System.out.println("Przed");
        continue;
      }
      System.out.println("Za");
      fleet.add(reinforcement);
      Thread assault = new Thread(reinforcement.siege);
      assault.start();
    }
  }
}
