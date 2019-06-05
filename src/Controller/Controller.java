package Controller;

import Model.Base;
import Model.Ship;
import Model.WarField;
import Model.WarFieldHandler;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Controller {

  private ArrayList<Ship> fleet;
  private WarFieldHandler handler;
  private View view;
  private Timer game_timer;
  private Timer nextWaveTimer;

  private Controller(){
    fleet = new ArrayList<>();
    Base defender = new Base(500);
    WarField place = new WarField(5, 4);
    handler = new WarFieldHandler(place, defender);
    game_timer = null;
    nextWaveTimer = null;
    view = new View(this);
    handler.getBase().setController(this);
  }

  public Runnable assaultBase = () -> {
    setTimer();
    setWaveTimer();
    nextWaveTimer.start();
    game_timer.start();
  };

  public void shotFired(int position){
    int row = position/5;
    int column = position % 5;
    if(handler.checkTarget(row, column)){
      for(Ship s: fleet){
        if(s.getRow() == row){
          s.destroyArmor(2);
          destroyShip(s);
          view.clearShip(position);
          break;
        }
      }
    }
  }

  public void stopAssault(){
    for(Ship s: fleet){
      if(s.getTimer() != null)
      s.stopTimer();
    }
    if(game_timer != null) {
      game_timer.stop();
    }
    if(nextWaveTimer != null){
      nextWaveTimer.stop();
    }
    game_timer = null;
    nextWaveTimer = null;
  }

  private void assault(){
    Ship reinforcement = handler.setShip(2, 2);
    if (reinforcement == null) {
      return;
    }
    view.drawShip(reinforcement.getRow() * 5 + reinforcement.getColumn());
    fleet.add(reinforcement);
    reinforcement.startTimer();
  }

  private void destroyShip(Ship wreckage){
    wreckage.stopTimer();
    handler.removeShip(wreckage);
    fleet.remove(wreckage);
  }

  public void destroyWholeFleet(){
    ArrayList<Ship> toRemove = new ArrayList<>(fleet.size());
    toRemove.addAll(fleet);
    for(Ship enemy: toRemove){
      destroyShip(enemy);
    }
  }

  private void setTimer(){
    game_timer = new Timer(1000, null);
    game_timer.addActionListener((ActionEvent actionevent) ->
            view.getScorePanel().displayClock());

    game_timer.addActionListener((ActionEvent actionevent) ->
            view.getScorePanel().nextWaveClock());

    game_timer.addActionListener((ActionEvent actionevent) -> assault());
  }

  private void setWaveTimer(){
    nextWaveTimer = new Timer(15000, null);
    nextWaveTimer.addActionListener((ActionEvent actionevent) -> nextWave());
  }

  private void nextWave() {
    if(game_timer.getDelay() > 100) {
      game_timer.setDelay(game_timer.getDelay() - 100);
    }
  }

  public Base getBase(){
    return handler.getBase();
  }

  public static void main(String [] args){
    new Controller();
  }
}