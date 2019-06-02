package Controller;

import Model.Base;
import Model.Ship;
import Model.WarField;
import Model.WarFieldHandler;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controller {

  private ArrayList<Ship> fleet;
  private WarFieldHandler handler;
  private View view;
  private Timer game_timer;
  private Timer nextWaveTimer;
  private int waveDelay;

  private Controller(){
    fleet = new ArrayList<>();
    Base defender = new Base(100);
    WarField place = new WarField(5, 4);
    handler = new WarFieldHandler(place, defender);
    game_timer = new Timer(1000, null);
    nextWaveTimer = new Timer(30000, null);
    waveDelay = 1;
    view = new View(this);
  }

  public Runnable assaultBase = () -> {
    setTimer();
    setWaveTimer();
    game_timer.start();
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
        TimeUnit.SECONDS.sleep(waveDelay);
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

  private void setTimer(){
    game_timer.addActionListener((ActionEvent actionevent) ->
      view.getScorePanel().displayClock());

    game_timer.addActionListener((ActionEvent actionevent) ->
      view.getScorePanel().nextWaveClock());
  }

  private void setWaveTimer(){
    nextWaveTimer.addActionListener((ActionEvent actionevent) -> {
      if(waveDelay > 0.2){
        waveDelay -= 0.2;
      }
    });
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